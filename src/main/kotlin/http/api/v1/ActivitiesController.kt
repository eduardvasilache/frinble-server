package http.api.v1

import http.api.base.BaseApiController
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.ActivitiesService

class ActivitiesController(vertx: Vertx,
                           private val activitiesService: ActivitiesService) : BaseApiController(vertx) {

    override fun configureRouter(): Router {
        router.get("/") { getAllActivities(it) }
        router.get("/:id") { getActivityById(it) }

        router.post("/") { createActivity(it) }
        router.delete("/:id") { deleteActivity(it) }

        router.post("/:id/join") { sendJoinRequest(it) }
        router.post("/:id/cancel") { cancelJoinRequest(it) }
        router.post("/:id/leave") { leaveActivity(it) }

        router.post("/:id/accept") { acceptUser(it) }
        router.post("/:id/deny") { denyUser(it) }

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