package api.v1

import api.base.BaseController
import helpers.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.ActivitiesService

class ActivitiesController(vertx: Vertx,
                           private val activitiesService: ActivitiesService) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.get("/").coroutineHandler { getAllActivities(it) }
        router.get("/:id").coroutineHandler { getActivityById(it) }

        router.post("/").coroutineHandler { createActivity(it) }
        router.delete("/:id").coroutineHandler { deleteActivity(it) }

        router.post("/:id/join").coroutineHandler { sendJoinRequest(it) }
        router.post("/:id/cancel").coroutineHandler { cancelJoinRequest(it) }
        router.post("/:id/leave").coroutineHandler { leaveActivity(it) }

        router.post("/:id/accept").coroutineHandler { acceptUser(it) }
        router.post("/:id/deny").coroutineHandler { denyUser(it) }

        return router
    }

    private suspend fun getAllActivities(context: RoutingContext) {

    }

    private suspend fun getActivityById(context: RoutingContext) {

    }

    private suspend fun createActivity(context: RoutingContext) {

    }

    private suspend fun deleteActivity(context: RoutingContext) {

    }

    private suspend fun sendJoinRequest(context: RoutingContext) {

    }

    private suspend fun cancelJoinRequest(context: RoutingContext) {

    }

    private suspend fun leaveActivity(context: RoutingContext) {

    }

    private suspend fun acceptUser(context: RoutingContext) {

    }

    private suspend fun denyUser(context: RoutingContext) {

    }

}