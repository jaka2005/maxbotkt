package funn.j2k.maxbotkt.model.chat

import funn.j2k.maxbotkt.model.Image
import funn.j2k.maxbotkt.model.message.Message
import funn.j2k.maxbotkt.model.user.ChatMember
import funn.j2k.maxbotkt.model.user.UserWithPhoto
import funn.j2k.maxbotkt.serializers.MilliInstant
import kotlinx.serialization.Serializable

@Serializable
data class Chat(
    val chatId: Long,
    val type: ChatType,
    val status: ChatStatus,
    val title: String?,
    val icon: Image?,
    val lastEventTime: MilliInstant,
    val participantsCount: Int,
    val ownerId: Long?,
    val participants: Map<String, MilliInstant>?,
    val isPublic: Boolean,
    val link: String?,
    val description: String?,
    val dialogWithUser: UserWithPhoto?,
    val chatMessageId: String?,
    val pinnedMessage: Message?
)
