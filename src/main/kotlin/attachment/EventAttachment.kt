package ru.netology.attachment

class EventAttachment(
    val event: Event
) : AbstractAttachment {
    override val type = "event"
}

data class Event(
    val id: Int,
    val time: Int,
    val memberStatus: Int,
    val isFavorite: Boolean,
    val address: String,
    val text: String,
    val buttonText: String,
    val friends: Array<Int>
)