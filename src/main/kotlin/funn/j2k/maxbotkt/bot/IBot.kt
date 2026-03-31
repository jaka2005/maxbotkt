package funn.j2k.maxbotkt.bot

import funn.j2k.maxbotkt.dto.SendMessage
import funn.j2k.maxbotkt.model.Marker
import funn.j2k.maxbotkt.model.Subscription
import funn.j2k.maxbotkt.model.TextFormat
import funn.j2k.maxbotkt.model.attachments.Attachment
import funn.j2k.maxbotkt.model.chat.Chat
import funn.j2k.maxbotkt.model.chat.ChatType
import funn.j2k.maxbotkt.model.message.Message
import funn.j2k.maxbotkt.model.message.Recipient
import funn.j2k.maxbotkt.model.update.Update
import funn.j2k.maxbotkt.model.update.UpdateType
import funn.j2k.maxbotkt.model.user.BotInfo

interface IBot {
    suspend fun deleteWebhook(url: String)
    suspend fun setWebhook(url: String, updateTypes: List<String>? = null, secret: String? = null)
    suspend fun getWebhooks(): List<Subscription>
    suspend fun answerCallback(callbackId: String, newMessage: SendMessage? = null, notification: String? = null)
    suspend fun editMessage(messageId: String, newMessage: SendMessage)
    suspend fun removeMessage(messageId: String)
    suspend fun getChats(count: Int = 50, marker: Marker? = null): Pair<List<Chat>, Marker?>
    suspend fun getChat(chatId: Long): Chat
    suspend fun leaveChat(chatId: Long)
    suspend fun sendMessage(recipient: Recipient, disableLinkPreview: Boolean = false, message: SendMessage): Message
    suspend fun getMe(): BotInfo
    suspend fun getUpdates(
        limit: Int = 100,
        timeoutSeconds: Int = 30,
        marker: Marker? = null,
        types: List<UpdateType>? = null
    ): Pair<List<Update>, Marker?>
}

suspend fun IBot.sendMessage(
    recipient: Recipient,
    text: String,
    disableLinkPreview: Boolean = false,
    notify: Boolean = true,
    format: TextFormat? = null,
    attachments: List<Attachment>? = null,
): Message = sendMessage(
    recipient = recipient,
    disableLinkPreview = disableLinkPreview,
    message = SendMessage(
        text = text,
        notify = notify,
        format = format,
        attachments = attachments,
    )
)

suspend fun IBot.sendMessage(
    text: String,
    chatId: Long? = null,
    userId: Long? = null,
    disableLinkPreview: Boolean = false,
    notify: Boolean = true,
    format: TextFormat? = null,
    attachments: List<Attachment>? = null,
): Message = sendMessage(
    // NOTE: chatType, doesnt have effect here
    recipient = Recipient(chatId = chatId, userId = userId, chatType = ChatType.DIALOG),
    disableLinkPreview = disableLinkPreview,
    message = SendMessage(
        text = text,
        notify = notify,
        format = format,
        attachments = attachments,
    )
)
