package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.model.message.Message
import funn.j2k.maxbotkt.model.user.toRecipient
import funn.j2k.maxbotkt.serializers.MilliInstant
import kotlinx.serialization.Serializable

@Serializable
data class MessageCreatedUpdate(
    override val timestamp: MilliInstant,
    val userLocale: String?,
    val message: Message,
) : Update

val MessageCreatedUpdate.from get() = message.sender?.toRecipient()
