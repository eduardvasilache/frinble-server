package repositories

import entities.AccountEntity
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.AsyncSQLClient
import utils.query
import utils.toIsoString
import utils.update
import java.time.ZonedDateTime
import javax.inject.Singleton

const val ACCOUNT_STATUS_UNCONFIRMED = 1
const val ACCOUNT_STATUS_CONFIRMED = 2
const val ACCOUNT_STATUS_LOCKED = 3

@Singleton
class AccountsRepository(sqlClient: AsyncSQLClient) : BaseRepository(sqlClient) {

    suspend fun getAccounts(): List<AccountEntity> {
        val query = """
            SELECT * FROM "users"
        """

        return getConnection().query(query).rows.map { it.toAccountEntity() }
    }

    suspend fun getAccountById(id: Long): AccountEntity? {
        val query = """
            SELECT * FROM "users"
            WHERE "id" = ?
        """

        val result = getConnection().query(query, listOf(id)).rows.first()
        return result.toAccountEntity()
    }

    suspend fun getAccountByEmail(email: String): AccountEntity? {
        val query = """
            SELECT * FROM "users"
            WHERE "email" = ?
        """

        val result = getConnection().query(query, listOf(email)).rows.first()
        return result.toAccountEntity()
    }

    suspend fun register(email: String, passwordHash: String, emailConfirmationToken: String): Long {
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

        val now = ZonedDateTime.now().toIsoString()

        val values = listOf(
                email,
                passwordHash,
                emailConfirmationToken,
                ACCOUNT_STATUS_UNCONFIRMED,
                now,
                now
        )

        val result = getConnection().query(query, values).rows.first()
        return result.getLong("id")
    }

    suspend fun confirmEmail(token: String): Boolean {
        val query = """
            UPDATE "users"
            SET
                "email_confirmation_token" = ?,
                "status" = ?,
                "updated_at" = ?
            WHERE "email_confirmation_token" = ?
        """

        val now = ZonedDateTime.now().toIsoString()

        val values = listOf(null, ACCOUNT_STATUS_CONFIRMED, now, token)

        val result = getConnection().update(query, values).updated

        return result > 0
    }

    suspend fun saveUpdatePasswordChallenge(userId: Long, challenge: String, challengeExpiryDate: ZonedDateTime): Boolean {
        val query = """
            UPDATE "users"
            SET
                "reset_password_token" = ?,
                "reset_password_token_expires_at" = ?,
                "updated_at" = ?
            WHERE "id" = ?
        """

        val now = ZonedDateTime.now().toIsoString()

        val values = listOf(challenge, challengeExpiryDate.toIsoString(), now, userId)

        val result = getConnection().update(query, values).updated

        return result > 0
    }

    suspend fun updatePassword(email: String, passwordHash: String): Boolean {
        val query = """
            UPDATE "users"
            SET
                "password_hash" = ?,
                "update_password_challenge" = ?,
                "update_password_challenge_expires_at" = ?,
                "updated_at" = ?
            WHERE "email" = ?
        """

        val now = ZonedDateTime.now().toIsoString()

        val values = listOf(passwordHash, null, null, now, email)

        val result = getConnection().update(query, values).updated

        return result > 0
    }

    private fun JsonObject.toAccountEntity() = AccountEntity(
            id = getLong("id"),
            status = getInteger("status"),
            firstName = getString("first_name"),
            lastName = getString("last_name"),
            email = getString("email"),
            birthDate = getString("born_at")?.let { ZonedDateTime.parse(it) },
            avatarUrl = getString("avatar_url"),
            passwordHash = getString("password_hash"),
            emailConfirmationToken = getString("email_confirmation_token"),
            updatePasswordChallenge = getString("update_password_challenge"),
            updatePasswordChallengeExpiryDate = getString("update_password_challenge_expires_at")?.let { ZonedDateTime.parse(it) },
            creationDate = getString("created_at").let { ZonedDateTime.parse(it) },
            lastUpdateDate = getString("updated_at")?.let { ZonedDateTime.parse(it) },
            lastLoginDate = getString("last_login_at")?.let { ZonedDateTime.parse(it) }
    )

}