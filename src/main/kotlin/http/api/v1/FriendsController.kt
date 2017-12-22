package http.api.v1

import http.api.BaseApiController
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.FriendsService

class FriendsController(vertx: Vertx,
                        private val friendsService: FriendsService) : BaseApiController(vertx) {

    override fun configureRouter(): Router {
        router.get("/", true) { getAllFriends(it) }

        router.post("/add") { sendFriendRequest(it) }
        router.post("/cancel") { cancelFriendRequest(it) }
        router.post("/accept") { acceptFriendRequest(it) }
        router.post("/deny") { denyFriendRequest(it) }

        router.get("/list") { getFriendLists(it) }
        router.post("/list") { createFriendList(it) }
        router.delete("/list/:id") { deleteFriendList(it) }

        router.post("/list/:id") { addFriendToFriendList(it) }
        router.delete("/list/:id") { removeFriendFromFriendList(it) }

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