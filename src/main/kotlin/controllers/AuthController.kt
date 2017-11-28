package controllers

import helpers.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import services.AuthService

class AuthController(vertx: Vertx,
                     private val authService: AuthService) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.get("/").coroutineHandler { }
        return router
    }

}