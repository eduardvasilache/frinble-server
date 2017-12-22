import io.vertx.core.Vertx
import io.vertx.ext.asyncsql.AsyncSQLClient
import io.vertx.ext.asyncsql.PostgreSQLClient
import io.vertx.ext.auth.jwt.JWTAuth
import io.vertx.ext.auth.jwt.JWTAuthOptions
import io.vertx.kotlin.core.json.JsonObject
import io.vertx.kotlin.ext.auth.KeyStoreOptions
import net.mready.photon.Injector
import net.mready.photon.Provides
import javax.inject.Singleton

fun createInjector(vertx: Vertx, config: AppConfig): Injector = Injector.Builder()
        .modules(AppModule(config), VertxModule(vertx), AuthModule())
        .autoInjectFields(true)
        .build()

class AppModule(private val config: AppConfig) {
    @Provides
    @Singleton
    fun config(): AppConfig = config
}

class VertxModule(private val vertx: Vertx) {

    @Provides
    @Singleton
    fun vertx(): Vertx = vertx

    @Provides
    fun sqlClient(vertx: Vertx, config: AppConfig): AsyncSQLClient = PostgreSQLClient.createShared(vertx, JsonObject(
            "host" to config.databaseConfig.host,
            "username" to config.databaseConfig.username,
            "password" to config.databaseConfig.password,
            "database" to config.databaseConfig.database
    ))

}

class AuthModule {

    @Provides
    @Singleton
    fun jwtAuth(vertx: Vertx): JWTAuth {
        val options = KeyStoreOptions()
        options.type = "jceks"
        options.path = "keystore.jceks"
        options.password = "secret"

        val config = JWTAuthOptions()
        config.keyStore = options

        return JWTAuth.create(vertx, config)
    }

}