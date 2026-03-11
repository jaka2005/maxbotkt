package funn.j2k.maxbotkt.model.attachments.button

import kotlinx.serialization.Serializable

@Serializable
data class CallbackButton(
    val text: String,
    val payload: String,
) : Button {
    init {
        require(text.length <= 128) { "Text length should be less then 128" }
        require(payload.length <= 1024) { "Payload length should be less then 128" }
    }
}
