package funn.j2k.maxbotkt.model.attachments.button

import kotlinx.serialization.Serializable

@Serializable
data class MessageButton(
    val text: String,
) : Button {
    init {
        require(text.length <= 128) { "Text length should be less then 128" }
    }
}
