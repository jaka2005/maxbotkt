package funn.j2k.maxbotkt.model.markup

import kotlinx.serialization.Serializable

@Serializable
data class MarkupElement(
    val type: MarkupElementType,
    val from: Int,
    val length: Int,
)
