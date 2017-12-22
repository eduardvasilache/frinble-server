package http.api

import http.BaseController
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.core.json.Json
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.ext.web.handler.JWTAuthHandler
import utils.applicationJson
import utils.coroutineHandler
import utils.textPlain
import javax.inject.Inject

data class ResponseWrapper(val error: ErrorResponse? = null,
                           val data: Any? = null)

data class ErrorResponse(val code: Int,
                         val message: String)

abstract class BaseApiController(vertx: Vertx) : BaseController(vertx) {

    @field:Inject
    private lateinit var jwtAuth: JWTAuth

    protected fun getParamOrThrow(context: RoutingContext, parameterName: String) =
            context.request().getParam(parameterName) ?: invalidParametersException(parameterName)

    private fun invalidParametersException(parameterName: String): Nothing
            = throw InvalidParameterException(parameterName)

    protected fun sendJsonResponse(context: RoutingContext, obj: Any?) {
        context.response().applicationJson().end(Json.encode(ResponseWrapper(data = obj)))
    }

    protected fun sendJsonError(context: RoutingContext, e: ApiException) {
        context.response().applicationJson().end(Json.encode(ResponseWrapper(error = ErrorResponse(e.code, e.message))))
    }

    protected fun Router.get(path: String, requireAuth: Boolean = true, fn: suspend (RoutingContext) -> Unit) {
        route(HttpMethod.GET, path, requireAuth, fn)
    }

    protected fun Router.post(path: String, requireAuth: Boolean = true, fn: suspend (RoutingContext) -> Unit) {
        route(HttpMethod.POST, path, requireAuth, fn)
    }

    protected fun Router.delete(path: String, requireAuth: Boolean = true, fn: suspend (RoutingContext) -> Unit) {
        route(HttpMethod.DELETE, path, requireAuth, fn)
    }

    private fun Router.route(method: HttpMethod, path: String, requireAuth: Boolean, fn: suspend (RoutingContext) -> Unit) {
        if (requireAuth) {
            route(method, path).handler(JWTAuthHandler.create(jwtAuth))
        }

        route(method, path).coroutineHandler {
            try {
                fn(it)
            } catch (e: ApiException) {
                sendJsonError(it, e)
            } catch (e: Exception) {
                it.response().setStatusCode(500).textPlain().end(e.toString())
            }
        }
    }

}