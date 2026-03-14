package funn.j2k.maxbotkt.model.message

import funn.j2k.maxbotkt.model.attachments.Attachment
import funn.j2k.maxbotkt.model.markup.MarkupElement
import kotlinx.serialization.Serializable

@Serializable
data class MessageBody(
    val mid: String,
    val seq: Long,
    val text: String,
    val attachments: List<Attachment>?,
    val markup: List<MarkupElement>?,
)
