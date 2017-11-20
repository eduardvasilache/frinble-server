package helpers

import io.vertx.core.json.JsonArray
import io.vertx.ext.sql.ResultSet
import io.vertx.ext.sql.SQLConnection
import io.vertx.kotlin.coroutines.awaitResult

suspend fun SQLConnection.query(sql: String, params: List<Any?>? = null): ResultSet =
        awaitResult { if (params != null) this.queryWithParams(sql, JsonArray(params), it) else this.query(sql, it) }