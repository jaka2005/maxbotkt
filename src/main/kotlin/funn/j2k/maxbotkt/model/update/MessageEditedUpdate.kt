package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.model.message.Message
import funn.j2k.maxbotkt.serializers.MilliInstant
import kotlinx.serialization.Serializable

@Serializable
data class MessageEditedUpdate(
    override val timestamp: MilliInstant,
    val message: Message,
) : Update

