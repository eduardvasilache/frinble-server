
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServer
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import kotlinx.coroutines.experimental.launch
import services.UserService

class HttpServer(private val userService: UserService) {

    private val server: HttpServer

    init {
        val router = Router.router(Vertx.vertx())

        router.get("/ok").handler { context ->
            context.response().end("OK")
        }

        router.get("/users").handler { context ->
            launch {
                val users = userService.getAllUsers()
                context.response().end(Json.encode(users))
            }
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