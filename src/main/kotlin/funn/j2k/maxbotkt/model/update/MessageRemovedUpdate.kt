package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.serializers.MilliInstant
import kotlinx.serialization.Serializable

@Serializable
data class MessageRemovedUpdate(
    override val timestamp: MilliInstant,
    val messageId: String,
    val chatId: Long,
    val userId: Long,
) : Update
