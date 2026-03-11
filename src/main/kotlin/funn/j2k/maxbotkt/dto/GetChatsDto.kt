package funn.j2k.maxbotkt.dto

import funn.j2k.maxbotkt.model.Marker
import funn.j2k.maxbotkt.model.chat.Chat
import kotlinx.serialization.Serializable

@Serializable
data class GetChatsDto(
    val marker: Marker?,
    val chats: List<Chat>,
)
