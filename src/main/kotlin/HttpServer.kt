
import http.api.v1.ApiController
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerOptions
import io.vertx.ext.web.Router

class HttpServer(apiController: ApiController) {

    private val server: HttpServer

    init {
        val router = Router.router(Vertx.vertx())

        router.mountSubRouter("/api", apiController.configureRouter())

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