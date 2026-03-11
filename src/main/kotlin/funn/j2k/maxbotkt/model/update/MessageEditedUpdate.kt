package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.model.message.Message
import kotlinx.serialization.Serializable

@Serializable
data class MessageEditedUpdate(
    override val timestamp: Long,
    val message: Message,
) : Update

