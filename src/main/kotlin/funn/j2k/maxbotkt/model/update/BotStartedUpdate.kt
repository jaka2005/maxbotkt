package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.model.user.BasicUser
import funn.j2k.maxbotkt.serializers.MilliInstant
import kotlinx.serialization.Serializable

@Serializable
data class BotStartedUpdate(
    override val timestamp: MilliInstant,
    val userLocale: String?,
    val chatId: Long,
    val user: BasicUser,
    val payload: String?,
) : Update
