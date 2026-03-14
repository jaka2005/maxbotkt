package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.model.user.BasicUser
import funn.j2k.maxbotkt.serializers.MilliInstant
import kotlinx.serialization.Serializable

@Serializable
data class BotAddedUpdate(
    override val timestamp: MilliInstant,
    val chatId: Long,
    val user: BasicUser,
) : Update