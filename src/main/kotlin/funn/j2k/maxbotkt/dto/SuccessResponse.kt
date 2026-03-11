package funn.j2k.maxbotkt.dto

import funn.j2k.maxbotkt.exception.RequestFailedException
import kotlinx.serialization.Serializable

@Serializable
data class SuccessResponse(
    val success: Boolean,
    val message: String?,
)

fun SuccessResponse.throwIfFailed() {
    if (!success) throw RequestFailedException(message)
}
