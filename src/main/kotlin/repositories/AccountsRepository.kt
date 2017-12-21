package repositories

import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.AsyncSQLClient
import models.Account
import org.mindrot.jbcrypt.BCrypt
import utils.query
import utils.toIsoString
import java.time.ZonedDateTime
import java.util.*
import javax.inject.Singleton

const val ACCOUNT_STATUS_UNCONFIRMED = 1
const val ACCOUNT_STATUS_CONFIRMED = 2
const val ACCOUNT_STATUS_LOCKED = 3

@Singleton
class UsersRepository(sqlClient: AsyncSQLClient) : BaseRepository(sqlClient) {

    suspend fun getAccounts(): List<Account> {
        val query = """
            SELECT * FROM "users"
        """

        return getConnection().query(query).rows.map { it.toAccount() }
    }

    suspend fun getAccountById(id: Long): Account? {
        val query = """
            SELECT * FROM "users"
            WHERE "id" = ?
        """

        val result = getConnection().query(query, listOf(id)).rows.firstOrNull() ?: return null

        return result.toAccount()
    }

    suspend fun register(email: String, password: String): Long {
        val query = """
            INSERT INTO "users" (
                "email",
                "password_hash",
                "email_confirmation_token",
                "status",
                "created_at",
                "updated_at"
            )
            VALUES (?,?,?,?,?,?)
            RETURNING "id"
        """

        val salt = BCrypt.gensalt()
        val hash = BCrypt.hashpw(password, salt)

        val now = ZonedDateTime.now().toIsoString()
        val emailConfirmationToken = UUID.randomUUID().toString()

        val values = listOf(
                email,
                hash,
                emailConfirmationToken,
                ACCOUNT_STATUS_UNCONFIRMED,
                now,
                now
        )

        val result = getConnection().query(query, values).rows.firstOrNull()

        return result!!.getLong("id")
    }

    private fun JsonObject.toAccount() = Account(
            id = getLong("id"),
            status = getInteger("status"),
            firstName = getString("first_name"),
            lastName = getString("last_name"),
            email = getString("email")
    )

}