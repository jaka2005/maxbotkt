package funn.j2k.maxbotkt.model.attachments.button

import funn.j2k.maxbotkt.serializers.Unknown
import kotlinx.serialization.json.JsonObject

data class UnknownButton(
    override val type: String?,
    override val raw: JsonObject,
) : Button, Unknown
