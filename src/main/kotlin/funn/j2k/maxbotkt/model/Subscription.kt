package funn.j2k.maxbotkt.model

import funn.j2k.maxbotkt.serializers.MilliInstant
import kotlinx.serialization.Serializable

@Serializable
data class Subscription(
    val url: String,
    val time: MilliInstant,
    val updateTypes: List<String>? // cant use UpdateType until it contains all the types
)
