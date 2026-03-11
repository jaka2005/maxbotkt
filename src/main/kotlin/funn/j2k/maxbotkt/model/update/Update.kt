package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.serializers.KPolymorphicSerializer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

@OptIn(ExperimentalSerializationApi::class)
@Serializable(with = KUpdateSerializer::class)
@JsonIgnoreUnknownKeys
sealed interface Update {
    val timestamp: Long
}

object KUpdateSerializer : KPolymorphicSerializer<Update, UpdateType, UnknowUpdate>(
    types = UpdateType.entries,
    discriminatorFieldName = "update_type",
    fallback = { type, obj ->
        val timestamp = obj["timestamp"]
            ?.jsonPrimitive
            ?.long ?: 0L

        UnknowUpdate(
            raw = obj,
            type = type,
            timestamp = timestamp
        )
    }
)
