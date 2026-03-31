package funn.j2k.maxbotkt.model.attachments.button

import funn.j2k.maxbotkt.serializers.PolymorphicType
import kotlin.reflect.KClass

enum class ButtonType(override val klass: KClass<out Button>) : PolymorphicType<Button> {
    CALLBACK(CallbackButton::class),
    MESSAGE(MessageButton::class),
}
