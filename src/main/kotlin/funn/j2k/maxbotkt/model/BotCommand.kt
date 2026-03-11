package funn.j2k.maxbotkt.model

import kotlinx.serialization.Serializable

@Serializable
data class BotCommand(
    val command: String,
    val description: String? = null,
)
