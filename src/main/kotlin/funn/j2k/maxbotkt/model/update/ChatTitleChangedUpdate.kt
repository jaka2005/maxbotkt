package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.model.user.BasicUser
import kotlinx.serialization.Serializable

@Serializable
data class ChatTitleChangedUpdate(
    override val timestamp: Long,
    val userLocale: String?,
    val chatId: Long,
    val user: BasicUser,
    val title: String,
) : Update
