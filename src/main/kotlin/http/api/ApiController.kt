package http.api

import http.api.v1.ActivitiesController
import http.api.v1.AuthController
import http.api.v1.FriendsController
import http.api.v1.UsersController
import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class ApiController(
    vertx: Vertx,
    private val authController: AuthController,
    private val usersController: UsersController,
    private val friendsController: FriendsController,
    private val activitiesController: ActivitiesController
) : BaseApiController(vertx) {

    override fun configureRouter(): Router {
        router.mountSubRouter("/v1/auth", authController.configureRouter())
        router.mountSubRouter("/v1/users", usersController.configureRouter())
        router.mountSubRouter("/v1/friends", friendsController.configureRouter())
        router.mountSubRouter("/v1/activities", activitiesController.configureRouter())
        return router
    }

}