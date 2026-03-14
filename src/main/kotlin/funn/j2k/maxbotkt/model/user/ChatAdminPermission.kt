package funn.j2k.maxbotkt.model.user

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class ChatAdminPermission {
    @SerialName("read_all_messages")
    READ_ALL_MESSAGES,

    @SerialName("add_remove_members")
    ADD_REMOVE_MEMBERS,

    @SerialName("add_admins")
    ADD_ADMINS,

    @SerialName("change_chat_info")
    CHANGE_CHAT_INFO,

    @SerialName("pin_message")
    PIN_MESSAGE,

    @SerialName("write")
    WRITE,

    @SerialName("can_call")
    CAN_CALL,

    @SerialName("edit_link")
    EDIT_LINK,

    @SerialName("post_edit_delete_message")
    POST_EDIT_DELETE_MESSAGE,

    @SerialName("edit_message")
    EDIT_MESSAGE,

    @SerialName("delete_message")
    DELETE_MESSAGE
}