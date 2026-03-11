package funn.j2k.maxbotkt.model.attachments.button

import funn.j2k.maxbotkt.serializers.KPolymorphicSerializer
import kotlinx.serialization.Serializable

@Serializable(with = KButtonSerializer::class)
sealed interface Button

object KButtonSerializer : KPolymorphicSerializer<Button, ButtonType, UnknownButton>(
    types = ButtonType.entries,
    fallback = { type, obj ->
        UnknownButton(
            raw = obj,
            type = type
        )
    }
)

