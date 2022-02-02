package ru.netology.attachment

class NoteAttachment(
    val note: Note
) : AbstractAttachment {
    override val type = "note"
}

data class Note(
    val id: Int,
    val ownerId: Int,
    val title: String,
    val text: String,
    val date: Int,
    val comments: Int,
    val readComments: Int,
    val viewUrl: String
)