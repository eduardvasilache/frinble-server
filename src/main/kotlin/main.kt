import io.vertx.core.Vertx
import io.vertx.core.VertxOptions

fun main(args: Array<String>) {
    val injector = createInjector(initVertx())

    val server = injector.instance(HttpServer::class.java)
    server.start(8080)
}

private fun initVertx(): Vertx = Vertx.vertx(VertxOptions())