package http.api.base

import http.base.BaseController
import http.exceptions.ApiException
import http.exceptions.InvalidParameterException
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import utils.applicationJson
import utils.coroutineHandler
import utils.textPlain

data class ResponseWrapper(val error: ErrorResponse? = null,
                           val data: Any? = null)

data class ErrorResponse(val code: Int,
                         val message: String)

abstract class BaseApiController(vertx: Vertx) : BaseController(vertx) {

    protected fun invalidParametersException(parameterName: String): Nothing
            = throw InvalidParameterException(parameterName)

    protected fun getParamOrThrow(context: RoutingContext, parameterName: String) =
            context.request().getParam(parameterName) ?: invalidParametersException(parameterName)

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
            // TODO
        }

        route(method, path).coroutineHandler {
            try {
                fn(it)
            } catch (e: ApiException) {
                it.response().applicationJson().end(Json.encode(ResponseWrapper(error = ErrorResponse(e.code, e.message))))
            } catch (e: Exception) {
                it.response().setStatusCode(500).textPlain().end(e.message)
            }
        }
    }

}