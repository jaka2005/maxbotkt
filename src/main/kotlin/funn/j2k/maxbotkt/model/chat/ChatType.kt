package funn.j2k.maxbotkt.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ChatType {
    @SerialName("chat")
    CHAT,
    @SerialName("dialog")
    DIALOG
}