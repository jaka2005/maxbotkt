package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.model.message.Message
import funn.j2k.maxbotkt.model.user.BasicUser
import funn.j2k.maxbotkt.serializers.MilliInstant
import kotlinx.serialization.Serializable

@Serializable
data class MessageCallbackUpdate(
    override val timestamp: MilliInstant,
    val userLocale: String?,
    val message: Message,
    val callback: Callback
) : Update

@Serializable
data class Callback(
    val timestamp: MilliInstant,
    val callbackId: String,
    val payload: String,
    val user: BasicUser,
)