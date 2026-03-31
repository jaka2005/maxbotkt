package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.serializers.KPolymorphicSerializer
import funn.j2k.maxbotkt.serializers.MilliInstant
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlin.time.Instant

@OptIn(ExperimentalSerializationApi::class)
@Serializable(with = KUpdateSerializer::class)
@JsonIgnoreUnknownKeys
sealed interface Update {
    val timestamp: MilliInstant
}

val Update.fromUserId get() = when(this) {
    is BotAddedUpdate -> this.user.userId
    is BotRemovedUpdate -> this.user.userId
    is BotStartedUpdate -> this.user.userId
    is ChatTitleChangedUpdate -> this.user.userId
    is MessageCallbackUpdate -> this.callback.user.userId
    is MessageCreatedUpdate -> this.message.sender?.userId
    is MessageEditedUpdate -> this.message.sender?.userId
    is MessageRemovedUpdate -> this.userId
    is UnknowUpdate -> null
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
            timestamp = Instant.fromEpochMilliseconds(timestamp)
        )
    }
)
