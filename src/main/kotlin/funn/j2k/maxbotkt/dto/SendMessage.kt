package funn.j2k.maxbotkt.dto

import funn.j2k.maxbotkt.model.TextFormat
import funn.j2k.maxbotkt.model.attachments.Attachment
import funn.j2k.maxbotkt.model.message.Message
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class SendMessage(
    val text: String,
    val notify: Boolean = true,
    val format: TextFormat? = null,
    val attachments: List<Attachment>? = null,
    val link: JsonObject? = null
)

@Serializable
data class SendMessageResponse(
    val message: Message,
)
