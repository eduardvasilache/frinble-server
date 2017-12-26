package http.api.v1

import http.api.BaseApiController
import io.vertx.core.Vertx
import io.vertx.core.json.Json
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTOptions
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import io.vertx.kotlin.core.json.JsonObject
import models.Account
import services.AuthService

class AuthController(vertx: Vertx,
                     private val authService: AuthService,
                     private val jwtAuth: JWTAuth) : BaseApiController(vertx) {

    override fun configureRouter(): Router {
        router.post("/register", false) { register(it) }
        router.post("/confirm", false) { confirmEmail(it) }
        router.post("/login", false) { login(it) }
        router.post("/password/reset", false) { resetPassword(it) }
        router.post("/password/reset/confirm", false) { confirmResetPassword(it) }

        return router
    }

    private suspend fun register(context: RoutingContext) {
        val email = getParamOrThrow(context, "email")
        val password = getParamOrThrow(context, "password")

        val account = authService.register(email, password)
        val token = generateToken(account)

        sendJsonResponse(context, JsonObject("token" to token, "account" to account))
    }

    private suspend fun confirmEmail(context: RoutingContext) {
        val token = getParamOrThrow(context, "token")

        val success = authService.confirmEmail(token)

        sendJsonResponse(context, Json.encode(success))
    }

    private suspend fun login(context: RoutingContext) {
        val email = getParamOrThrow(context, "email")
        val password = getParamOrThrow(context, "password")

        val account = authService.login(email, password)
        val token = generateToken(account)

        sendJsonResponse(context, JsonObject("token" to token, "account" to account))
    }

    private suspend fun resetPassword(context: RoutingContext) {
        val userId = getParamOrThrow(context, "userId")

        context.response().end("Trying to reset password for user with id $userId")
    }

    private suspend fun confirmResetPassword(context: RoutingContext) {
        val token = getParamOrThrow(context, "token")

        context.response().end("Trying to confirm password reset for token $token")
    }

    private fun generateToken(account: Account): String {
        return jwtAuth.generateToken(JsonObject("userId" to account.id), JWTOptions())
    }

}