package ru.netology

import java.time.LocalDateTime

typealias UserId = Long

data class Message(
    val id: Long,
    val to: UserId,
    val from: UserId,
    val message: String,
    val eventDt: LocalDateTime,
    var view: Boolean = false
)

data class Chat(
    val id: Long,
    val createdDt: LocalDateTime,
    val to: UserId,
    val from: UserId,
    val message: MutableList<Message> = mutableListOf()
)

class ChatService {

    val chats: MutableMap<UserId, MutableList<Chat>> = mutableMapOf()

    fun getChats(userId: UserId): List<Chat> =
        chats[userId]?.map { chat ->
            chat.copy(message = chat.message.asSequence().sortedByDescending { it.eventDt }.take(1).toMutableList())
        } ?: listOf()

    fun getUnreadChatsCount(userId: UserId): Int {
        return chats[userId]?.count { chat -> chat.message.first { !it.view } != null } ?: 0
    }

    fun getChat(userId: UserId, chatId: Long, messageId: Long, messageCount: Int): List<Message> {
        val chat = chats[userId]?.firstOrNull { it.id == chatId } ?: return listOf()
        val messages = chat.message.asSequence().sortedBy { it.id }
        val indexMessage = messages.indexOfFirst { it.id == messageId }
        val result = messages.toList().subList(indexMessage, indexMessage + messageCount)
        result.onEach { message ->
            chat.message.first { message.id == it.id }.apply {
                this.view = true
            }
        }
        return result
    }

    fun deleteChat(userId: UserId, chatId: Long) {
        val chat = checkNotNull(chats[userId]?.firstOrNull { chat -> chat.id == chatId }) { "Chat is not found" }
        chats[userId]?.minusElement(chat)
    }

    fun sendMessage(to: UserId, from: UserId, text: String) {
        val findedFromChats = if (chats.containsKey(from)) {
            chats[from]
        } else {
            chats.put(from, mutableListOf())
        }

        val chat = findedFromChats?.asSequence()?.firstOrNull { it.to == to }
            ?: Chat(
                id = findedFromChats?.asSequence()?.maxByOrNull { it.id }?.id?.plus(1) ?: 1,
                createdDt = LocalDateTime.now(),
                to = to,
                from = from
            ).also {
                checkNotNull(chats[from]).add(it)
            }

        val findedToChats = if (chats.containsKey(to)) {
            chats[to]
        } else {
            chats.put(to, mutableListOf())
        }

        val toChat = findedToChats?.asSequence()?.firstOrNull { it.from == from } ?: Chat(
            id = findedToChats?.asSequence()?.maxByOrNull { it.id }?.id?.plus(1) ?: 1,
            createdDt = LocalDateTime.now(),
            to = from,
            from = to
        ).also {
            checkNotNull(chats[to]).add(it)
        }

        val toMessage = Message(
            id = chat.message?.asSequence().maxByOrNull { it.id }?.id?.plus(1) ?: 1,
            to = to,
            from = from,
            message = text,
            eventDt = LocalDateTime.now(),
            view = true
        )

        val fromMessage = Message(
            id = toChat.message.maxByOrNull { it.id }?.id?.plus(1) ?: 1,
            to = to,
            from = from,
            message = text,
            eventDt = LocalDateTime.now(),
            view = false
        )

        chat.message.add(toMessage)
        toChat.message.add(fromMessage)
    }

    fun deleteMessage(userId: UserId, chatId: Long, messageId: Long) {
        val chat = checkNotNull(chats[userId]?.firstOrNull { chat -> chat.id == chatId }) { "Chat is not found" }
        val messages = chat.message.asSequence().sortedBy { it.id }.toMutableList()
        messages.removeIf { it.id == messageId }
        if (messages.size == 0) {
            chats[userId]?.removeIf { it.id == chatId }
        }
    }

}