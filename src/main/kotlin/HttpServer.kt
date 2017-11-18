import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router

class HttpServer {

    private val server: HttpServer

    init {
        val router = Router.router(Vertx.vertx())

        router.get().handler { context ->
            context.response().end("Yay!")
        }

        server = Vertx.vertx()
                .createHttpServer(HttpServerOptions())
                .requestHandler { router.accept(it) }
    }

    fun start(port: Int) {
        server.listen(port) {
            if (it.succeeded()) {
                println("Listening on ${server.actualPort()}")
            } else {
                throw it.cause()
            }
        }
    }

}