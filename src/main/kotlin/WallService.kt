package ru.netology

class WallService {

    var posts = arrayOf<Post?>()
    var id: Int = 1

    fun add(post: Post) : Post {
        post.id = id //задаем элементу уникальный id.
        id++ // увеличиваем id для сохранения уникальности
        posts = posts.copyOf(posts.size + 1) // расширяем массив на 1, копируя все элементы
        posts[posts.size - 1] = post //заполняем последний элемент массива
        return post // возвращаем добавленный post с новым id
    }

    fun update(post: Post) : Boolean {
        // ищем пост, у которого id совпадает с тем, что мы подаем на вход
        var postToEdit = posts.find { postFromArray -> postFromArray?.id == post.id }
        if (postToEdit == null) // если не нашли, выходим из функции с результатом false
            return false

        // сюда идем, только если нашли нужный пост, но блок else не нужен, потому что до этой точки код все равно дойдет
        // только в том случае, если условие выше не выполнится

        // обновляем все поля класса
        postToEdit.canDelete = post.canDelete
        postToEdit.canEdit = post.canEdit
        // TODO Для все остальных полей, кроме id и date
        return true // возвращаем true
    }
}