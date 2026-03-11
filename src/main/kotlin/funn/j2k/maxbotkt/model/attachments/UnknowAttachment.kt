package funn.j2k.maxbotkt.model.attachments

import funn.j2k.maxbotkt.serializers.Unknown
import kotlinx.serialization.json.JsonObject

data class UnknowAttachment(
    override val raw: JsonObject,
    override val type: String?
) : Attachment, Unknown
