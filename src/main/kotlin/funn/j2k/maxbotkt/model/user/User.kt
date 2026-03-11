package funn.j2k.maxbotkt.model.user

import funn.j2k.maxbotkt.model.BotCommand
import funn.j2k.maxbotkt.model.chat.ChatType
import funn.j2k.maxbotkt.model.message.Recipient
import kotlinx.serialization.Serializable

sealed interface User {
    val userId: Long
    val firstName: String
    val lastName: String?
    val username: String?
    val isBot: Boolean
    val lastActivityTime: Long?
    val name: String? // deprecated
}

fun User.toRecipient() = Recipient(
    chatType = ChatType.DIALOG,
    userId = userId
)

@Serializable
data class BasicUser(
    override val userId: Long,
    override val firstName: String,
    override val lastName: String?,
    override val username: String?,
    override val isBot: Boolean,
    override val lastActivityTime: Long?,
    override val name: String?,
) : User

@Serializable
data class UserWithPhoto(
    override val userId: Long,
    override val firstName: String,
    override val lastName: String?,
    override val username: String?,
    override val isBot: Boolean,
    override val lastActivityTime: Long?,
    override val name: String?,

    val avatarUrl: String?,
    val description: String?,
) : User

@Serializable
data class BotInfo(
    override val userId: Long,
    override val firstName: String,
    override val lastName: String?,
    override val username: String?,
    override val isBot: Boolean,
    override val lastActivityTime: Long?,
    override val name: String?,

    val description: String?,
    val avatarUrl: String?,
    val fullAvatarUrl: String?,
    val commands: List<BotCommand>?,
) : User

@Serializable
data class ChatMember(
    override val userId: Long,
    override val firstName: String,
    override val lastName: String?,
    override val username: String?,
    override val isBot: Boolean,
    override val lastActivityTime: Long?,
    override val name: String?,

    val avatarUrl: String?,
    val description: String?,

    val role: String?,
    val joinedAt: Long?,
) : User
