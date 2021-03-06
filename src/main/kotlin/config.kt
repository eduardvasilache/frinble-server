import com.fasterxml.jackson.annotation.JsonProperty
import io.vertx.core.Vertx
import utils.jsonDecode

fun readConfig(vertx: Vertx, filename: String): AppConfig =
    jsonDecode(vertx.fileSystem().readFileBlocking(filename).toString(Charsets.UTF_8))

data class AppConfig(
    @JsonProperty("database") val databaseConfig: DatabaseConfig,
    @JsonProperty("email") val emailConfig: EmailConfig
)

data class DatabaseConfig(
    @JsonProperty("host") val host: String,
    @JsonProperty("username") val username: String,
    @JsonProperty("password") val password: String,
    @JsonProperty("database") val database: String
)

data class EmailConfig(
    @JsonProperty("host") val host: String,
    @JsonProperty("port") val port: Int,
    @JsonProperty("username") val username: String,
    @JsonProperty("password") val password: String
)