package funn.j2k.maxbotkt.model.attachments

import funn.j2k.maxbotkt.serializers.KPolymorphicSerializer
import kotlinx.serialization.Serializable

@Serializable(with = KAttachmentSerializer::class)
sealed interface Attachment {
}

object KAttachmentSerializer : KPolymorphicSerializer<Attachment, AttachmentType, UnknowAttachment>(
    types = AttachmentType.entries,
    fallback = { type, obj ->
        UnknowAttachment(
            raw = obj,
            type = type
        )
    }
)
