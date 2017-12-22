package serializers

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.core.JsonTokenId
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.module.SimpleModule
import utils.toIsoString
import java.time.ZonedDateTime

object TimeSerializerModule : SimpleModule() {
    init {
        addSerializer(ZonedDateTime::class.java, ZonedDateTimeSerializer)
        addDeserializer(ZonedDateTime::class.java, ZonedDateTimeDeserializer)
    }
}

object ZonedDateTimeSerializer : JsonSerializer<ZonedDateTime>() {
    override fun serialize(value: ZonedDateTime, gen: JsonGenerator, serializers: SerializerProvider?) {
        gen.writeString(value.toIsoString())
    }
}

object ZonedDateTimeDeserializer : JsonDeserializer<ZonedDateTime>() {
    override fun deserialize(parser: JsonParser, context: DeserializationContext): ZonedDateTime? {
        if (parser.hasTokenId(JsonTokenId.ID_STRING)) {
            val string = parser.text.trim()
            if (string.isBlank()) {
                return null
            }
            return ZonedDateTime.parse(parser.text.trim())
        }

        throw context.wrongTokenException(parser, ZonedDateTime::class.java, JsonToken.VALUE_STRING, "Expected string.")
    }
}