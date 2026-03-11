package funn.j2k.maxbotkt.model.message

import funn.j2k.maxbotkt.model.attachments.Attachment
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class MessageBody(
    val mid: String,
    val seq: Long,
    val text: String,
    val attachments: List<Attachment>?,
    val markup: JsonObject?,
)
