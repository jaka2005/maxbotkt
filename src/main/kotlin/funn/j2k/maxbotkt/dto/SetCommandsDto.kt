package funn.j2k.maxbotkt.dto

import funn.j2k.maxbotkt.model.BotCommand
import kotlinx.serialization.Serializable

@Serializable
data class SetCommandsDto(
    val commands: List<BotCommand>
)
