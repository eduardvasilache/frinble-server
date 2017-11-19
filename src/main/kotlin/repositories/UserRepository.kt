package repositories

import helpers.getConnection
import helpers.query
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.AsyncSQLClient
import models.User
import javax.inject.Singleton

@Singleton
class UserRepository(private val sqlClient: AsyncSQLClient) {

    suspend fun getAllUsers(): List<User> {
        val query = """
            SELECT * FROM users
        """

        sqlClient.getConnection().use { sql ->
            return sql.query(query).rows.map { it.toUser() }
        }
    }

    private fun JsonObject.toUser() = User(
            id = getLong("id"),
            firstName = getString("first_name"),
            lastName = getString("last_name"),
            email = getString("email")
    )

}