package http.api.v1

import http.api.BaseApiController
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.UsersService

class UsersController(vertx: Vertx,
                      private val usersService: UsersService) : BaseApiController(vertx) {

    override fun configureRouter(): Router {
        router.get("/") { context -> getAllUsers(context) }
        router.get("/self") { context -> getCurrentUser(context) }
        router.post("/self") { context -> updateCurrentUser(context) }
        router.get("/:id") { context -> getUserById(context) }
        return router
    }

    private suspend fun getAllUsers(context: RoutingContext) {
        val users = usersService.getAllUsers()

        sendJsonResponse(context, users)
    }

    private suspend fun getUserById(context: RoutingContext) {
        val id = getParamOrThrow(context, "id").toLong()
        val user = usersService.getUserById(id)

        sendJsonResponse(context, user)
    }

    private suspend fun getCurrentUser(context: RoutingContext) {
        val id = context.user().principal().getLong("userId")
        val user = usersService.getUserById(id)

        sendJsonResponse(context, user)
    }

    private suspend fun updateCurrentUser(context: RoutingContext) {

    }

}