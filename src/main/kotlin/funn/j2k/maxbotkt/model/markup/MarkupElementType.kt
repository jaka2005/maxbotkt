package funn.j2k.maxbotkt.model.markup

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class MarkupElementType {
    @SerialName("strong")
    STRONG,

    @SerialName("emphasized")
    EMPHASIZED,

    @SerialName("monospaced")
    MONOSPACED,

    @SerialName("link")
    LINK,

    @SerialName("strikethrough")
    STRIKETHROUGH,

    @SerialName("underline")
    UNDERLINE,

    @SerialName("user_mention")
    USER_MENTION
}