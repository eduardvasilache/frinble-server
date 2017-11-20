package repositories

import io.vertx.ext.asyncsql.AsyncSQLClient
import io.vertx.ext.sql.SQLConnection
import io.vertx.kotlin.coroutines.awaitResult

abstract class BaseRepository(private val sqlClient: AsyncSQLClient) {

    // TODO: Close connection?
    suspend fun getConnection(): SQLConnection = awaitResult { sqlClient.getConnection(it) }

}