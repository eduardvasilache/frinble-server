package api.v1

import api.base.BaseController
import helpers.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.UsersService

class UsersController(vertx: Vertx,
                      private val usersService: UsersService) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.get("/").coroutineHandler { context -> getAllUsers(context) }
        router.get("/:id").coroutineHandler { context -> getUserById(context) }
        router.get("/me").coroutineHandler { context -> getCurrentUser(context) }
        router.post("/me").coroutineHandler { context -> updateCurrentUser(context) }
        return router
    }

    private suspend fun getAllUsers(context: RoutingContext) {
        val users = usersService.getAllUsers()
        context.response().end(Json.encode(users))
    }

    private suspend fun getUserById(context: RoutingContext) {

    }

    private suspend fun getCurrentUser(context: RoutingContext) {

    }

    private suspend fun updateCurrentUser(context: RoutingContext) {

    }

    private suspend fun createTestUser(context: RoutingContext) {
        usersService.createTestUser()
        getAllUsers(context)
    }

}