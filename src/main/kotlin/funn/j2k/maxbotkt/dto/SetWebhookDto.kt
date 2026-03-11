package funn.j2k.maxbotkt.dto

import kotlinx.serialization.Serializable

@Serializable
data class SetWebhookDto(
    val url: String,
    val updateTypes: List<String>? = null,
    // NOTE: it's not bot token it's random event verification secret
    val secret: String? = null
)
