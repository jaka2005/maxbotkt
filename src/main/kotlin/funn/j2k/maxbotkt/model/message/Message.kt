package funn.j2k.maxbotkt.model.message

import funn.j2k.maxbotkt.dto.SendMessage
import funn.j2k.maxbotkt.model.chat.ChatType
import funn.j2k.maxbotkt.model.user.BasicUser
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Message(
    val sender: BasicUser?,
    val recipient: Recipient,
    val timestamp: Long,
    val link: JsonObject?,
    val body: MessageBody,
    val stat: JsonObject?,
    val url: String?
)

fun Message.toSendMessage() = SendMessage(
    text = body.text,
    attachments = body.attachments,
    link = link,
)

@Serializable
data class Recipient(
    val chatId: Long? = null,
    val chatType: ChatType,
    val userId: Long? = null,
)