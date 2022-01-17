import org.junit.Test

import org.junit.Assert.*
import ru.netology.Post
import ru.netology.WallService

class WallServiceTests {

    @Test
    fun add_idShouldBeNotZero() {

        // Arrange
        val wallService = WallService()

        // Заполняем Post любыми параметрами,
        // в данном тесте не имеет значения, какими
        val postToAdd = Post(0,
            1,
            1,
            1,
            1,
            "123",
            1,
            1,
            true,
            1,
            1,
            1,
            1,
            1,
            "test",
            1,
            true,
            false,
            false,
            false,
            false,
            false,
            1,
            1
            )

        // act
        val result = wallService.add(postToAdd)


        assertTrue(result.id != 0)
    }

    @Test
    fun update_postExistsInArray_shouldReturnTrue() {

        // Arrange
        val wallService = WallService()

        // добавляем несколько постов в массив
        // здесь уже указываем id
        wallService.add(Post(1,
            1,
            1,
            1,
            1,
            "123",
            1,
            1,
            true,
            1,
            1,
            1,
            1,
            1,
            "test",
            1,
            true,
            false,
            false,
            false,
            false,
            false,
            1,
            1))
        wallService.add(Post(2,
            1,
            1,
            1,
            1,
            "123",
            1,
            1,
            true,
            1,
            1,
            1,
            1,
            1,
            "test",
            1,
            true,
            false,
            false,
            false,
            false,
            false,
            1,
            1))
        wallService.add(Post(3,
            1,
            1,
            1,
            1,
            "123",
            1,
            1,
            true,
            1,
            1,
            1,
            1,
            1,
            "test",
            1,
            true,
            false,
            false,
            false,
            false,
            false,
            1,
            1))


        // делаем пост, который будет приходить на функцию update
        // у него такой же id, как у одного из постов в массиве
        val postToUpdate = Post(1,
            1,
            1,
            1,
            1,
            "123",
            1,
            1,
            true,
            1,
            1,
            1,
            1,
            1,
            "test",
            1,
            true,
            false,
            false,
            false,
            false,
            false,
            1,
            1)

        // act
        val result = wallService.update(postToUpdate)


        assertTrue(result)
    }

    @Test
    fun update_postNotExistsInArray_shouldReturnFalse() {

        // Arrange
        val wallService = WallService()

        // добавляем несколько постов в массив
        // здесь уже указываем id
        wallService.add(Post(1,
            1,
            1,
            1,
            1,
            "123",
            1,
            1,
            true,
            1,
            1,
            1,
            1,
            1,
            "test",
            1,
            true,
            false,
            false,
            false,
            false,
            false,
            1,
            1))
        wallService.add(Post(2,
            1,
            1,
            1,
            1,
            "123",
            1,
            1,
            true,
            1,
            1,
            1,
            1,
            1,
            "test",
            1,
            true,
            false,
            false,
            false,
            false,
            false,
            1,
            1))
        wallService.add(Post(3,
            1,
            1,
            1,
            1,
            "123",
            1,
            1,
            true,
            1,
            1,
            1,
            1,
            1,
            "test",
            1,
            true,
            false,
            false,
            false,
            false,
            false,
            1,
            1))

        val postToUpdate = Post(1000,
            1,
            1,
            1,
            1,
            "123",
            1,
            1,
            true,
            1,
            1,
            1,
            1,
            1,
            "test",
            1,
            true,
            false,
            false,
            false,
            false,
            false,
            1,
            1)

        // act
        val result = wallService.update(postToUpdate)


        assertFalse(result)
    }
}