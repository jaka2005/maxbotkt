package funn.j2k.maxbotkt.callback

import funn.j2k.maxbotkt.exception.InvalidCallbackDataType
import funn.j2k.maxbotkt.model.attachments.button.CallbackButton
import funn.j2k.maxbotkt.model.update.MessageCallbackUpdate
import kotlinx.serialization.json.Json

val callbackJson = Json {
    prettyPrint = false
}

inline fun <reified T : Any> serializeCallbackData(payload: T, json: Json = callbackJson): String {
    val cls = payload::class
    val typeAnnotation = cls.annotations.find { it is CallbackData } as CallbackData?
    val type = typeAnnotation?.type

    val encoded = json.encodeToString(payload)

    if (type != null) {
        return "${type}.$encoded"
    }
    return encoded
}

inline fun <reified T : Any> deserializeCallbackData(rawPayload: String, json: Json = callbackJson): T? {
    val cls = T::class
    val typeAnnotation = cls.annotations.find { it is CallbackData } as CallbackData?
    val type = typeAnnotation?.type

    var cleanPayload = rawPayload
    if (type != null) {
        if (!rawPayload.startsWith("$type."))
            return null

        cleanPayload = rawPayload.removePrefix("$type.")
    }

    return json.decodeFromString(cleanPayload)
}

inline fun <reified T : Any> CallbackButton(text: String, payload: T) =
    CallbackButton(text, serializeCallbackData(payload))

inline fun <reified T : Any> MessageCallbackUpdate.payload(): T =
    deserializeCallbackData(callback.payload) ?: throw InvalidCallbackDataType(callback.payload)

inline fun <reified T : Any> MessageCallbackUpdate.payloadOrNull(): T? =
    deserializeCallbackData(callback.payload)
