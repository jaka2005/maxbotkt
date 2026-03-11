package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.model.message.Message
import kotlinx.serialization.Serializable

@Serializable
data class MessageCreatedUpdate(
    override val timestamp: Long,
    val userLocale: String?,
    val message: Message,
) : Update
