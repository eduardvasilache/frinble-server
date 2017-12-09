import io.vertx.core.Vertx

private const val CONFIG_FILENAME = "config.json"
private const val SERVER_PORT = 80

fun main(args: Array<String>) {
    val vertx = Vertx.vertx()
    val config = readConfig(vertx, CONFIG_FILENAME)
    val injector = createInjector(vertx, config)

    val server = injector.instance(HttpServer::class.java)
    server.start(SERVER_PORT)
}