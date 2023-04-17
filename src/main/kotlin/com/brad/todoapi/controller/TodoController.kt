package com.brad.todoapi.controller

import com.brad.todoapi.helper.MathConstant.MIN_AVAILABLE_ID
import com.brad.todoapi.models.*
import com.brad.todoapi.services.TodoService
import com.brad.todoapi.services.UsersService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.security.Principal
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
@RequestMapping("/api/v1/todos")
class TodoController (private val todoService: TodoService, private val userService: UsersService) {

    @PostMapping
    fun createTodo(@Valid @RequestBody todo: Todo): Todo {
        val user = userService.getUserByUsername(todo.user.username) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.")
        todo.user = user
        return todoService.createTodo(todo)
    }

    @GetMapping
    fun getAllTodos(@RequestBody user : Users): List<Todo> {
        val getUser = userService.getUserByUsername(user.username) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.")

        return if (getUser.role == UserRoles.ADMIN) {
            todoService.getAllTodos()
        } else {
            todoService.getAllTodosByUser(user)
        }
    }

    @GetMapping("/{id}")
    fun getTodoById(@PathVariable @Min(MIN_AVAILABLE_ID) id: Long): Todo =
        todoService.getTodoById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 $id 입니다.")

    @PutMapping("/{id}")
    fun updateTodoById(@PathVariable @Min(MIN_AVAILABLE_ID) id: Long, @RequestBody updatedTodo: Todo): Todo? {
        val user = userService.getUserByUsername(updatedTodo.user.username) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.")

        val existingTodo = todoService.getTodoById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 $id 입니다.")

        if (user.role == UserRoles.ADMIN || existingTodo.user.role == UserRoles.USER) {
            return todoService.updateTodoById(id, updatedTodo)
        }
        else {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다." )
        }
    }


    @DeleteMapping("/{id}")
    fun deleteTodoById(@PathVariable @Min(MIN_AVAILABLE_ID) id: Long, @RequestBody user : Users): ResponseEntity<Void> {
        val getUser = userService.getUserByUsername(user.username) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.")

        val existingTodo = todoService.getTodoById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 $id 입니다.")

        if (getUser.role == UserRoles.ADMIN || existingTodo.user.role == UserRoles.USER) {
            todoService.deleteTodoById(id)
            return ResponseEntity(HttpStatus.OK)
        }
        else {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다." )
        }
    }

    @PostMapping("/{id}/status")
    fun changeTodoStatusById(@PathVariable @Min(MIN_AVAILABLE_ID) id: Long, @RequestBody todoStatusUpdateRequest: TodoStatusUpdateRequest): Todo? {
        val getUser = userService.getUserByUsername(todoStatusUpdateRequest.user.username) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 사용자입니다.")
        val existingTodo = todoService.getTodoById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 $id 입니다.")

        if (getUser.role == UserRoles.ADMIN || existingTodo.user.role == UserRoles.USER) {
            return todoService.changeTodoStatusById(id, todoStatusUpdateRequest.statusUpdate)
        }
        else {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "권한이 없습니다." )
        }
    }
}


data class StatusUpdate(val status: TodoStatus)

data class TodoStatusUpdateRequest(
    val statusUpdate: StatusUpdate,
    val user: Users
)