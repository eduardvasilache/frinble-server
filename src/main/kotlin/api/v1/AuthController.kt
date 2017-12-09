package api.v1

import api.base.BaseController
import helpers.coroutineHandler
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.AuthService

class AuthController(vertx: Vertx,
                     private val authService: AuthService) : BaseController(vertx) {

    override fun configureRouter(): Router {
        router.post("/login").coroutineHandler { login(it) }
        router.post("/register").coroutineHandler { register(it) }
        router.post("/password/reset").coroutineHandler { resetPassword(it) }
        router.post("/password/reset/confirm").coroutineHandler { confirmResetPassword(it) }
        router.post("/revoke_tokens").coroutineHandler { revokeTokens(it) }
        return router
    }

    private suspend fun login(context: RoutingContext) {
        val email = getParamOrThrow(context, "email")
        val password = getParamOrThrow(context, "password")

        context.response().end("Trying to login with email $email and password $password")
    }

    private suspend fun register(context: RoutingContext) {
        val email = getParamOrThrow(context, "email")
        val password = getParamOrThrow(context, "password")

        context.response().end("Trying to register an user with email $email and password $password")
    }

    private suspend fun resetPassword(context: RoutingContext) {
        val userId = getParamOrThrow(context, "userId")

        context.response().end("Trying to reset password for user with id $userId")
    }

    private suspend fun confirmResetPassword(context: RoutingContext) {
        val token = getParamOrThrow(context, "token")

        context.response().end("Trying to confirm password reset for token $token")
    }

    private suspend fun revokeTokens(context: RoutingContext) {
        val userId = getParamOrThrow(context, "userId")

        context.response().end("Trying to revoke all tokens for user with id $userId")
    }

}