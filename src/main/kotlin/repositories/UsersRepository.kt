package repositories

import helpers.query
import helpers.update
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.AsyncSQLClient
import models.User
import javax.inject.Singleton

@Singleton
class UsersRepository(sqlClient: AsyncSQLClient) : BaseRepository(sqlClient) {

    suspend fun getAllUsers(): List<User> {
        val query = """
            SELECT * FROM "UserAccounts"
        """

        return getConnection().query(query).rows.map { it.toUser() }
    }

    suspend fun createTestUser(): Boolean {
        val query = """
            INSERT INTO "UserAccounts" (
                "Email",
                "FirstName",
                "LastName",
                "CreatedAt",
                "UpdatedAt",
                "AccountStatus"
            )
            VALUES (?,?,?,?,?,?)
        """

        val values = listOf(
                "example@example.com",
                "John",
                "Doe",
                "2017-11-28T18:09:03.427+02:00",
                "2017-11-28T18:09:03.427+02:00",
                "0"
        )

        return getConnection().update(query, values).updated == 1
    }

    private fun JsonObject.toUser() = User(
            id = getLong("Id"),
            firstName = getString("FirstName"),
            lastName = getString("LastName"),
            email = getString("Email")
    )

}