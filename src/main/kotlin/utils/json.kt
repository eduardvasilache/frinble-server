package utils

import io.vertx.core.json.Json

inline fun <reified T> jsonDecode(str: String): T = Json.decodeValue(str, T::class.java)