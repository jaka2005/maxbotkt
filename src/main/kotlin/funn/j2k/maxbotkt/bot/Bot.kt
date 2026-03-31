package funn.j2k.maxbotkt.bot

import funn.j2k.maxbotkt.dto.AnswerCallbackDto
import funn.j2k.maxbotkt.dto.GetChatsDto
import funn.j2k.maxbotkt.dto.GetUpdatesDto
import funn.j2k.maxbotkt.dto.SendMessage
import funn.j2k.maxbotkt.dto.SendMessageResponse
import funn.j2k.maxbotkt.dto.SetWebhookDto
import funn.j2k.maxbotkt.dto.SuccessResponse
import funn.j2k.maxbotkt.dto.throwIfFailed
import funn.j2k.maxbotkt.json
import funn.j2k.maxbotkt.model.Marker
import funn.j2k.maxbotkt.model.Subscription
import funn.j2k.maxbotkt.model.chat.Chat
import funn.j2k.maxbotkt.model.message.Message
import funn.j2k.maxbotkt.model.message.Recipient
import funn.j2k.maxbotkt.model.update.Update
import funn.j2k.maxbotkt.model.update.UpdateType
import funn.j2k.maxbotkt.model.user.BotInfo
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*

const val MAX_ORIGIN = "https://platform-api.max.ru/"

class Bot(
    val origin: String = MAX_ORIGIN,
    private val token: String,
) : IBot {
    val client = HttpClient(CIO) {
        expectSuccess = true
        install(ContentNegotiation) {
            json(json)
        }
        defaultRequest {
            url(origin)
            header("Authorization", token)
        }
    }

    override suspend fun getUpdates(
        limit: Int,
        timeoutSeconds: Int,
        marker: Marker?,
        types: List<UpdateType>?
    ): Pair<List<Update>, Marker?> {
        val response = try {
            client.get("updates") {
                parameter("limit", limit)
                parameter("timeout", timeoutSeconds)
                marker?.let { parameter("marker", it.id) }
                types?.let { parameter("types", it.joinToString(",")) }
            }
        } catch (_: HttpRequestTimeoutException) {
            return Pair(emptyList(), marker)
        }

        val result = response.body<GetUpdatesDto>()
        return result.updates to result.marker
    }

    override suspend fun getMe(): BotInfo {
        val response = client.get("me")

        return response.body<BotInfo>()
    }

    override suspend fun sendMessage(
        recipient: Recipient,
        disableLinkPreview: Boolean,
        message: SendMessage,
    ): Message {
        val response = client.post("messages") {
            recipient.userId?.let { parameter("user_id", it) }
            recipient.chatId?.let { parameter("chat_id", it) }
            parameter("disable_link_preview", disableLinkPreview)

            contentType(ContentType.Application.Json)
            setBody(message)
        }

        return response.body<SendMessageResponse>().message
    }

    override suspend fun getChat(chatId: Long): Chat {
        val response = client.get("chats") {
            url {
                appendPathSegments(chatId.toString())
            }
        }

        return response.body()
    }

    override suspend fun leaveChat(chatId: Long) {
        client.delete("chats") {
            url {
                appendPathSegments(chatId.toString())
                appendPathSegments("members", "me")
            }
        }.body<SuccessResponse>().throwIfFailed()
    }

    override suspend fun getChats(
        count: Int,
        marker: Marker?
    ): Pair<List<Chat>, Marker?> {
        val response = client.get("chats") {
            parameter("count", count)
            marker?.let { parameter("marker", it.id) }
        }.body<GetChatsDto>()

        return response.chats to response.marker
    }

    override suspend fun removeMessage(messageId: String) {
        client.delete("messages") {
            parameter("message_id", messageId)
        }.body<SuccessResponse>().throwIfFailed()
    }

    override suspend fun editMessage(messageId: String, newMessage: SendMessage) {
        client.put("messages") {
            parameter("message_id", messageId)

            contentType(ContentType.Application.Json)
            setBody(newMessage)
        }.body<SuccessResponse>().throwIfFailed()
    }

    override suspend fun answerCallback(
        callbackId: String,
        newMessage: SendMessage?,
        notification: String?,
    ) {
        client.post("answers") {
            parameter("callback_id", callbackId)
            contentType(ContentType.Application.Json)
            setBody(AnswerCallbackDto(newMessage, notification))
        }.body<SuccessResponse>().throwIfFailed()
    }

    override suspend fun getWebhooks(): List<Subscription> {
        return client.get("subscriptions").body()
    }

    override suspend fun setWebhook(
        url: String,
        updateTypes: List<String>?,
        secret: String?,
    ) {
        client.post("subscriptions") {
            contentType(ContentType.Application.Json)
            setBody(SetWebhookDto(url, updateTypes, secret))
        }.body<SuccessResponse>().throwIfFailed()
    }

    override suspend fun deleteWebhook(url: String) {
        client.delete("subscriptions") {
            parameter("url", url)
        }.body<SuccessResponse>().throwIfFailed()
    }
}
