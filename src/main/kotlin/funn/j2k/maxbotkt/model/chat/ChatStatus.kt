package funn.j2k.maxbotkt.model.chat

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ChatStatus {
    @SerialName("active")
    ACTIVE,
    @SerialName("removed")
    REMOVED,
    @SerialName("left")
    LEFT,
    @SerialName("closed")
    CLOSED,
}