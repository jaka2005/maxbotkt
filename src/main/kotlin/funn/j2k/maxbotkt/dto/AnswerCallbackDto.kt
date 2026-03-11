package funn.j2k.maxbotkt.dto

import kotlinx.serialization.Serializable

@Serializable
data class AnswerCallbackDto(
    val message: SendMessage?,
    val notification: String?,
)
