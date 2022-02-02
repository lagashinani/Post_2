package ru.netology.attachment

class PhotoAttachment(
    val photo: Photo
) : AbstractAttachment {
    override val type = "photo"
}

data class Photo(
    val id: Int,
    val albumId: Int,
    val ownerId: Int,
    val userId: Int,
    val text: String,
    val date: Int,
    val updated: Int,
    val sizes: Array<Size>
) {
    data class Size(
        val type: String,
        val url: String,
        val width: String,
        val height: String
    )
}