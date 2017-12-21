package http.api.v1

import http.api.base.BaseApiController
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.UsersService
import utils.applicationJson

class UsersController(vertx: Vertx,
                      private val usersService: UsersService) : BaseApiController(vertx) {

    override fun configureRouter(): Router {
        router.get("/") { context -> getAllUsers(context) }
        router.get("/:id") { context -> getUserById(context) }
        router.get("/me") { context -> getCurrentUser(context) }
        router.post("/me") { context -> updateCurrentUser(context) }
        return router
    }

    private suspend fun getAllUsers(context: RoutingContext) {
        val users = usersService.getAllUsers()
        context.response().applicationJson().end(Json.encode(users))
    }

    private suspend fun getUserById(context: RoutingContext) {

    }

    private suspend fun getCurrentUser(context: RoutingContext) {

    }

    private suspend fun updateCurrentUser(context: RoutingContext) {

    }

}