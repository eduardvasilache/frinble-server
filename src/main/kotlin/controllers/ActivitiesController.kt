package controllers

import helpers.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import services.ActivitiesService

class ActivitiesController(vertx: Vertx,
                           private val activitiesService: ActivitiesService) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.get("/").coroutineHandler { }
        return router
    }

}