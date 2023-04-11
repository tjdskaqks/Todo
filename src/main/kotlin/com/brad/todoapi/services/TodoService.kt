package com.brad.todoapi.services

import com.brad.todoapi.controller.StatusUpdate
import com.brad.todoapi.models.Todo
import com.brad.todoapi.models.TodoStatus
import com.brad.todoapi.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
@Service
class TodoService(private val todoRepository: TodoRepository) {
    fun createTodo (todo: Todo) : Todo = todoRepository.save(todo)

    fun getAllTodos(): List<Todo> = todoRepository.findAll()

    fun getTodoById(id: Long): Todo? = todoRepository.findByIdOrNull(id)

    fun updateTodoById(id: Long, updatedTodo: Todo): Todo? {
        val existingTodo = getTodoById(id) ?: return null

        existingTodo.apply {
            title = updatedTodo.title.takeIf { it != title } ?: title
            content = updatedTodo.content.takeIf { it != content } ?: content
            status = updatedTodo.status.takeIf { it != status } ?: status
            completeAt = if (status == TodoStatus.COMPLETE) LocalDateTime.now() else null
            updateAt = LocalDateTime.now()
        }

        return todoRepository.save(existingTodo)
    }

    fun deleteTodoById(id: Long) = todoRepository.deleteById(id)

    fun changeTodoStatusById(id: Long, statusUpdate: StatusUpdate): Todo? {
        val existingTodo = getTodoById(id) ?: return null

        existingTodo.apply {
            status = statusUpdate.status
            completeAt = if (status == TodoStatus.COMPLETE) LocalDateTime.now() else null
            updateAt = LocalDateTime.now()
        }

        return todoRepository.save(existingTodo)
    }
}