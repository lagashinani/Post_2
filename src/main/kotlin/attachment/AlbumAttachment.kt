package ru.netology.attachment

class AlbumAttachment(
   val album: Album
) : AbstractAttachment {
    override val type = "album"
}

data class Album(
    val id: Int,
    val thumb: PhotoAttachment,
    val ownerId: Int,
    val title: String,
    val description: String,
    val created: Int,
    val updated: Int,
    val size: Int
)