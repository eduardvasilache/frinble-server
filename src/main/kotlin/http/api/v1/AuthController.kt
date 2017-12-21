package http.api.v1

import http.api.base.BaseApiController
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import services.AuthService
import utils.applicationJson

class AuthController(vertx: Vertx,
                     private val authService: AuthService) : BaseApiController(vertx) {

    override fun configureRouter(): Router {
        router.post("/register", false) { register(it) }
        router.post("/login", false) { login(it) }
        router.post("/password/reset", false) { resetPassword(it) }
        router.post("/password/reset/confirm", false) { confirmResetPassword(it) }
        router.post("/revoke_tokens", false) { revokeTokens(it) }

        return router
    }

    private suspend fun register(context: RoutingContext) {
        val email = getParamOrThrow(context, "email")
        val password = getParamOrThrow(context, "password")

        val account = authService.register(email, password)

        context.response().applicationJson().end(Json.encode(account))
    }

    private suspend fun login(context: RoutingContext) {
        val email = getParamOrThrow(context, "email")
        val password = getParamOrThrow(context, "password")

        context.response().end("Trying to login with email $email and password $password")
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