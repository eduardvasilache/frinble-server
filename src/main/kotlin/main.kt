import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import http.HttpServer
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import serializers.TimeSerializerModule

private const val CONFIG_FILENAME = "config.json"
private const val SERVER_PORT = 8080

fun main(args: Array<String>) {
    val vertx = Vertx.vertx()
    val config = readConfig(vertx, CONFIG_FILENAME)
    val injector = createInjector(vertx, config)

    Json.mapper.registerKotlinModule().registerModule(TimeSerializerModule)

    val server = injector.instance(HttpServer::class.java)
    server.start(SERVER_PORT)
}