package funn.j2k.maxbotkt.serializers

import kotlinx.serialization.json.JsonObject

interface Unknown {
    val type: String?
    val raw: JsonObject
}