package helpers

import io.vertx.core.json.JsonArray
import io.vertx.ext.asyncsql.AsyncSQLClient
import io.vertx.ext.sql.ResultSet
import io.vertx.ext.sql.SQLConnection
import kotlin.coroutines.experimental.suspendCoroutine

suspend fun AsyncSQLClient.getConnection(): SQLConnection = suspendCoroutine { cont ->
    getConnection { res ->
        if (res.succeeded()) {
            cont.resume(res.result())
        } else {
            cont.resumeWithException(res.cause())
        }
    }
}

suspend fun SQLConnection.query(sql: String, params: List<Any?>? = null): ResultSet =
        withHandler { if (params != null) this.queryWithParams(sql, JsonArray(params), it) else this.query(sql, it) }