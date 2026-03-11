package funn.j2k.maxbotkt.dto

import funn.j2k.maxbotkt.model.Marker
import funn.j2k.maxbotkt.model.update.Update
import kotlinx.serialization.Serializable

@Serializable
data class GetUpdatesDto(
    val updates: List<Update>,
    val marker: Marker? = null,
)
