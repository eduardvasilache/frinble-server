import io.vertx.core.Vertx
import io.vertx.ext.asyncsql.AsyncSQLClient
import io.vertx.ext.asyncsql.PostgreSQLClient
import io.vertx.kotlin.core.json.JsonObject
import net.frinble.BuildConfig
import net.mready.photon.Injector
import net.mready.photon.Provides
import javax.inject.Singleton

fun createInjector(vertx: Vertx): Injector = Injector.Builder()
        .modules(VertxModule(vertx))
        .autoInjectFields(true)
        .build()

class VertxModule(val vertx: Vertx) {

    @Provides
    @Singleton
    fun vertx(): Vertx = vertx

    @Provides
    fun sqlClient(vertx: Vertx): AsyncSQLClient = PostgreSQLClient.createShared(vertx, JsonObject(
            "host" to BuildConfig.DB_HOST,
            "username" to BuildConfig.DB_USER,
            "password" to BuildConfig.DB_PASS,
            "database" to BuildConfig.DB_NAME
    ))

}