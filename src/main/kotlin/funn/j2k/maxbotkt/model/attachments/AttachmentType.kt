package funn.j2k.maxbotkt.model.attachments

import funn.j2k.maxbotkt.serializers.PolymorphicType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.reflect.KClass

@Serializable
enum class AttachmentType(override val klass: KClass<out Attachment>) : PolymorphicType<Attachment> {
    @SerialName("inline_keyboard")
    INLINE_KEYBOARD(InlineKeyboard::class)
}