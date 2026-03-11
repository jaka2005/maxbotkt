package funn.j2k.maxbotkt.model.attachments

import funn.j2k.maxbotkt.model.attachments.button.Button
import kotlinx.serialization.Serializable

@Serializable
data class InlineKeyboard(
    val payload: InlineKeyboardPayload,
) : Attachment {
}

@Serializable
data class InlineKeyboardPayload(
    val buttons: List<List<Button>>
)
