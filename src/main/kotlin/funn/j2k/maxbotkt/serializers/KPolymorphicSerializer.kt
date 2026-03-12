package funn.j2k.maxbotkt.serializers

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import kotlin.enums.EnumEntries

abstract class KPolymorphicSerializer<T : Any, E, out U>(
    val types: EnumEntries<E>,
    val discriminatorFieldName: String = "type",
    val fallback: (type: String?, obj: JsonObject) -> T,
) : KSerializer<T>
        where U : T, E : Enum<E>, E : PolymorphicType<T> {

    @OptIn(ExperimentalSerializationApi::class)
    private val classMap =
        types.associateBy { it.klass }

    private val nameMap =
        types.associateBy { it.name.uppercase() }

    override val descriptor = JsonObject.serializer().descriptor

    override fun deserialize(decoder: Decoder): T {
        val jsonDecoder = decoder as JsonDecoder
        val element = jsonDecoder.decodeJsonElement()
        val obj = element.jsonObject

        val discriminator = obj[discriminatorFieldName]
            ?.jsonPrimitive
            ?.content

        if (discriminator == null) {
            return fallback(discriminator, obj)
        }

        val type = nameMap[discriminator.uppercase()]
            ?: return fallback(discriminator, obj)

        val cleanObj = JsonObject(obj.filterKeys { it != discriminatorFieldName })
        return jsonDecoder.json.decodeFromJsonElement(type.serializer(), cleanObj)
    }

    override fun serialize(encoder: Encoder, value: T) {
        val jsonEncoder = encoder as JsonEncoder

        if (value is Unknown) {
            jsonEncoder.encodeJsonElement(value.raw)
            return
        }

        val type = classMap[value::class]
            ?: error("Unknown polymorphic type: ${value::class}")

        @Suppress("UNCHECKED_CAST")
        val serializer = type.serializer() as KSerializer<T>

        val element = jsonEncoder.json.encodeToJsonElement(serializer, value)

        val obj = buildJsonObject {
            put(discriminatorFieldName, type.name.lowercase())
            element.jsonObject.forEach { (k, v) -> put(k, v) }
        }

        jsonEncoder.encodeJsonElement(obj)
    }
}