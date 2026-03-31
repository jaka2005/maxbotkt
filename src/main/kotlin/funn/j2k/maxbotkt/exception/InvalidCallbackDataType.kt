package funn.j2k.maxbotkt.exception

class InvalidCallbackDataType(val type: String) : RuntimeException("Invalid callback data type: $type")