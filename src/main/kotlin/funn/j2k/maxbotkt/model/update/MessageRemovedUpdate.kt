package funn.j2k.maxbotkt.model.update

import kotlinx.serialization.Serializable

@Serializable
data class MessageRemovedUpdate(
    override val timestamp: Long,
    val messageId: String,
    val chatId: Long,
    val userId: Long,
) : Update
