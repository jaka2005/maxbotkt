package funn.j2k.maxbotkt.model.update

import funn.j2k.maxbotkt.serializers.MilliInstant
import funn.j2k.maxbotkt.serializers.Unknown
import kotlinx.serialization.json.JsonObject

data class UnknowUpdate(
    override val raw: JsonObject,
    override val type: String?,
    override val timestamp: MilliInstant,
) : Update, Unknown