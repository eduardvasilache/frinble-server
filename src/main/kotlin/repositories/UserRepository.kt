package repositories

import helpers.query
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.AsyncSQLClient
import models.User
import javax.inject.Singleton

@Singleton
class UserRepository(sqlClient: AsyncSQLClient) : BaseRepository(sqlClient) {

    suspend fun getAllUsers(): List<User> {
        val query = """
            SELECT * FROM users
        """

        return getConnection().query(query).rows.map { it.toUser() }
    }

    private fun JsonObject.toUser() = User(
            id = getLong("id"),
            firstName = getString("first_name"),
            lastName = getString("last_name"),
            email = getString("email")
    )

}