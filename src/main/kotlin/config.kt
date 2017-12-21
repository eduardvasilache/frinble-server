import com.fasterxml.jackson.annotation.JsonProperty
import io.vertx.core.Vertx
import utils.jsonDecode

fun readConfig(vertx: Vertx, filename: String): AppConfig =
        jsonDecode(vertx.fileSystem().readFileBlocking(filename).toString(Charsets.UTF_8))

data class AppConfig(@JsonProperty("database") val databaseConfig: DatabaseConfig)

data class DatabaseConfig(@JsonProperty("host") val host: String,
                          @JsonProperty("username") val username: String,
                          @JsonProperty("password") val password: String,
                          @JsonProperty("database") val database: String)