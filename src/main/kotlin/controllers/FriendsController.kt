package controllers

import helpers.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import services.FriendsService

class FriendsController(vertx: Vertx,
                        private val friendsService: FriendsService) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.get("/").coroutineHandler { }
        return router
    }

}