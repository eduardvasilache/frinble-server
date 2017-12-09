package api.base

import api.exceptions.InvalidParameterException
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext

abstract class BaseController(vertx: Vertx) {

    val router: Router = Router.router(vertx)

    abstract fun configureRouter(): Router

    protected fun invalidParametersException(parameterName: String): Nothing
            = throw InvalidParameterException(parameterName)

    protected fun getParamOrThrow(context: RoutingContext, parameterName: String) =
            context.request().getParam(parameterName) ?: invalidParametersException(parameterName)

}