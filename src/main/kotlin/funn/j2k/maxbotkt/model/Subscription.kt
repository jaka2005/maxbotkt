package funn.j2k.maxbotkt.model

import kotlinx.serialization.Serializable

@Serializable
data class Subscription(
    val url: String,
    val time: Long,
    val updateTypes: List<String>? // cant use UpdateType until it contains all the types
)
