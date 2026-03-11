package funn.j2k.maxbotkt

import funn.j2k.maxbotkt.dto.AnswerCallbackDto
import funn.j2k.maxbotkt.dto.GetChatsDto
import funn.j2k.maxbotkt.dto.GetUpdatesDto
import funn.j2k.maxbotkt.dto.SendMessage
import funn.j2k.maxbotkt.dto.SendMessageResponse
import funn.j2k.maxbotkt.dto.SetCommandsDto
import funn.j2k.maxbotkt.dto.SetWebhookDto
import funn.j2k.maxbotkt.dto.SuccessResponse
import funn.j2k.maxbotkt.dto.throwIfFailed
import funn.j2k.maxbotkt.model.BotCommand
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

class Bot(val origin: String = MAX_ORIGIN, private val token: String) {
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

    suspend fun getUpdates(
        limit: Int = 100,
        timeoutSeconds: Int = 30 ,
        marker: Marker? = null,
        types: List<UpdateType>? = null
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

    suspend fun getMe(): BotInfo {
        val response = client.get("me")

        return response.body<BotInfo>()
    }

    @Deprecated("its not working already")
    suspend fun setCommands(commands: List<BotCommand>) {
        client.patch("me") {
            contentType(ContentType.Application.Json)
            setBody(SetCommandsDto(commands))
        }
    }

    suspend fun sendMessage(
        recipient: Recipient,
        disableLinkPreview: Boolean = false,
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

    suspend fun getChat(chatId: Long): Chat {
        val response = client.get("chats") {
            url {
                appendPathSegments(chatId.toString())
            }
        }

        return response.body()
    }

    suspend fun getChats(
        count: Int = 50,
        marker: Marker? = null
    ): Pair<List<Chat>, Marker?> {
        val response = client.get("chats") {
            parameter("count", count)
            marker?.let { parameter("marker", it.id) }
        }.body<GetChatsDto>()

        return response.chats to response.marker
    }

    suspend fun removeMessage(messageId: String) {
        client.delete("messages") {
            parameter("message_id", messageId)
        }.body<SuccessResponse>().throwIfFailed()
    }

    suspend fun editMessage(messageId: String, newMessage: SendMessage) {
        client.put("messages") {
            parameter("message_id", messageId)

            contentType(ContentType.Application.Json)
            setBody(newMessage)
        }.body<SuccessResponse>().throwIfFailed()
    }

    suspend fun answerCallback(
        callbackId: String,
        newMessage: SendMessage? = null,
        notification: String? = null,
    ) {
        client.post("answers") {
            parameter("callback_id", callbackId)
            contentType(ContentType.Application.Json)
            setBody(AnswerCallbackDto(newMessage, notification))
        }.body<SuccessResponse>().throwIfFailed()
    }

    suspend fun getWebhooks(): List<Subscription> {
        return client.get("subscriptions").body()
    }

    suspend fun setWebhook(
        url: String,
        updateTypes: List<String>? = null,
        secret: String? = null,
    ) {
        client.post("subscriptions") {
            contentType(ContentType.Application.Json)
            setBody(SetWebhookDto(url, updateTypes, secret))
        }.body<SuccessResponse>().throwIfFailed()
    }

    suspend fun deleteWebhook(url: String) {
        client.delete("subscriptions") {
            parameter("url", url)
        }.body<SuccessResponse>().throwIfFailed()
    }
}
