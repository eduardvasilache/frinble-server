package controllers

import io.vertx.core.Vertx
import io.vertx.ext.web.Router

class ApiController(vertx: Vertx,
                    private val authController: AuthController,
                    private val usersController: UsersController,
                    private val friendsController: FriendsController,
                    private val activitiesController: ActivitiesController) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.mountSubRouter("/v1/auth", authController.configureRouter())
        router.mountSubRouter("/v1/users", usersController.configureRouter())
        router.mountSubRouter("/v1/friends", friendsController.configureRouter())
        router.mountSubRouter("/v1/activities", activitiesController.configureRouter())
        return router
    }

}