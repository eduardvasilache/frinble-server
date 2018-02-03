package utils

import io.netty.handler.codec.http.HttpHeaderNames
import io.vertx.core.http.HttpServerResponse

fun HttpServerResponse.applicationJson(): HttpServerResponse =
    putHeader(HttpHeaderNames.CONTENT_TYPE, "application/json; charset=utf-8")

fun HttpServerResponse.textPlain(): HttpServerResponse =
    putHeader(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=utf-8")

fun HttpServerResponse.textHtml(): HttpServerResponse =
    putHeader(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=utf-8")