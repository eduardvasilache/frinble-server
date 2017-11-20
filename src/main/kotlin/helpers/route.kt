package helpers

import io.vertx.ext.web.Route
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.experimental.launch

fun Route.coroutineHandler(fn: suspend (RoutingContext) -> Unit) {
    handler { context ->
        launch(context.vertx().dispatcher()) {
            try {
                fn(context)
            } catch (e: Exception) {
                context.fail(e)
            }
        }
    }
}