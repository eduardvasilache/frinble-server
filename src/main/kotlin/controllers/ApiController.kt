package controllers

import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class ApiController(vertx: Vertx,
                    private val userController: UserController) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.mountSubRouter("/v1/users", userController.configureRouter())
        return router
    }

}