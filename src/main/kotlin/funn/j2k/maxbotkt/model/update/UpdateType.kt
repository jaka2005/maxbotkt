package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.serializers.PolymorphicType
import kotlin.reflect.KClass

enum class UpdateType(
    override val klass: KClass<out Update>,
) : PolymorphicType<Update> {
    MESSAGE_CREATED(MessageCreatedUpdate::class),
    MESSAGE_CALLBACK(MessageCallbackUpdate::class),
    BOT_STARTED(BotStartedUpdate::class),
    BOT_ADDED(BotAddedUpdate::class),
    BOT_REMOVED(BotRemovedUpdate::class),
    CHAT_TITLE_CHANGED(ChatTitleChangedUpdate::class),
    MESSAGE_EDITED(MessageEditedUpdate::class),
    MESSAGE_REMOVED(MessageRemovedUpdate::class)
}