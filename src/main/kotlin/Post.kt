package ru.netology

data class Post(
    var id: Int?,
    var ownerId: Int?,
    var fromId: Int?,
    var createdBy: Int?,
    var date: Int?,
    var text: String?,
    var replyOwnerId: Int?,
    var replyPostId: Int?,
    var friendsOnly: Boolean?,
    var comments: Any?,
    var copyright: Any?,
    var likes: Any?,
    var reposts: Any?,
    var views: Any?,
    var postType: String?,
    var signerId: Int?,
    var canPin: Boolean?,
    var canDelete: Boolean?,
    var canEdit: Boolean?,
    var isPinned: Boolean?,
    var markedAsAds: Boolean?,
    var isFavorite: Boolean?,
    var donut: Any?,
    var postponedId: Int?
)