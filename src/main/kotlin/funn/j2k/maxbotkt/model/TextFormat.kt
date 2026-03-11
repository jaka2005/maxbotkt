package funn.j2k.maxbotkt.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class TextFormat {
    @SerialName("markdown")
    MARKDOWN,
    @SerialName("html")
    HTML
}