package http

import io.vertx.core.Vertx
import io.vertx.ext.web.Router

abstract class BaseController(vertx: Vertx) {

    val router: Router = Router.router(vertx)

    abstract fun configureRouter(): Router

}