package api.v1

import api.base.BaseController
import helpers.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.FriendsService

class FriendsController(vertx: Vertx,
                        private val friendsService: FriendsService) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.get("/").coroutineHandler { getAllFriends(it) }

        router.post("/add").coroutineHandler { sendFriendRequest(it) }
        router.post("/cancel").coroutineHandler { cancelFriendRequest(it) }
        router.post("/accept").coroutineHandler { acceptFriendRequest(it) }
        router.post("/deny").coroutineHandler { denyFriendRequest(it) }

        router.get("/list").coroutineHandler { getFriendLists(it) }
        router.post("/list").coroutineHandler { createFriendList(it) }
        router.delete("/list/:id").coroutineHandler { deleteFriendList(it) }

        router.post("/list/:id").coroutineHandler { addFriendToFriendList(it) }
        router.delete("/list/:id").coroutineHandler { removeFriendFromFriendList(it) }

        return router
    }

    private suspend fun getAllFriends(context: RoutingContext) {

    }

    private suspend fun sendFriendRequest(context: RoutingContext) {

    }

    private suspend fun cancelFriendRequest(context: RoutingContext) {

    }

    private suspend fun acceptFriendRequest(context: RoutingContext) {

    }

    private suspend fun denyFriendRequest(context: RoutingContext) {

    }

    private suspend fun getFriendLists(context: RoutingContext) {

    }

    private suspend fun createFriendList(context: RoutingContext) {

    }

    private suspend fun deleteFriendList(context: RoutingContext) {

    }

    private suspend fun addFriendToFriendList(context: RoutingContext) {

    }

    private suspend fun removeFriendFromFriendList(context: RoutingContext) {

    }

}