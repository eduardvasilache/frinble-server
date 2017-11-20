package controllers

import helpers.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.UserService

class UserController(vertx: Vertx,
                     private val userService: UserService) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.get("/getAllUsers").coroutineHandler { context -> getAllUsers(context) }
        return router
    }

    private suspend fun getAllUsers(context: RoutingContext) {
        val users = userService.getAllUsers()
        context.response().end(Json.encode(users))
    }

}