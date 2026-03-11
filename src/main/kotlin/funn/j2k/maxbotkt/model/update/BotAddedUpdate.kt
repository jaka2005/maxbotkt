package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.model.user.BasicUser
import kotlinx.serialization.Serializable

@Serializable
data class BotAddedUpdate(
    override val timestamp: Long,
    val chatId: Long,
    val user: BasicUser,
) : Update