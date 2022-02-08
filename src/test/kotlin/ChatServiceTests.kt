import org.junit.Test

import org.junit.Assert.*
import ru.netology.ChatService
import ru.netology.Post
import ru.netology.WallService

class ChatServiceTests {

    @Test
    fun `get empty chats by user`() {
        // Arrange
        val chatService = ChatService()

        // act
        val result = chatService.getChats(userId = 0)

        assertTrue(result.isEmpty())
    }

    @Test
    fun `get chats by user after send message`() {
        // Arrange
        val chatService = ChatService()

        // act
        chatService.sendMessage(to = 1, from = 0, text = "Test")

        val result = chatService.getChats(userId = 0)

        assertTrue(result.size == 1)
        assertTrue(result.first().to == 1L)
        assertTrue(result.first().message.first().message == "Test")
        assertTrue(result.first().message.first().view)

        val result2 = chatService.getChats(userId = 1)
        assertTrue(result2.size == 1)
        assertTrue(result2.first().to == 0L)
        assertTrue(result2.first().message.first().message == "Test")
        assertFalse(result2.first().message.first().view)

        chatService.chats.clear()
    }

    @Test
    fun `delete last message`() {
        // Arrange
        val chatService = ChatService()

        // act
        chatService.sendMessage(to = 1, from = 0, text = "Test")

        val result = chatService.getChats(userId = 0)

        assertTrue(result.size == 1)
        assertTrue(result.first().to == 1L)
        assertTrue(result.first().message.size == 1)

        chatService.deleteMessage(userId = 0, result.first().id, result.first().message.first().id)

        val result2 = chatService.getChats(userId = 0)
        assertTrue(result2.isEmpty())
    }

}