package com.brad.todoapi.controller

import com.brad.todoapi.helper.MathConstant.MIN_AVAILABLE_ID
import com.brad.todoapi.models.*
import com.brad.todoapi.services.TodoService
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/v1/todos")
class TodoController (private val todoService: TodoService) {

    @PostMapping
    suspend fun createTodo(@Valid @RequestBody todo: Todo): Todo = withContext(Dispatchers.IO) {todoService.createTodo(todo)}

    @GetMapping
    suspend fun getAllTodos(): List<Todo> = withContext(Dispatchers.IO) {todoService.getAllTodos() }

    @GetMapping("/{id}")
    fun getTodoById(@PathVariable @Min(MIN_AVAILABLE_ID) id: Long): Todo =
        todoService.getTodoById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 $id 입니다.")

    @PutMapping("/{id}")
    fun updateTodoById(@PathVariable @Min(MIN_AVAILABLE_ID) id: Long, @RequestBody updatedTodo: Todo): Todo =
        todoService.updateTodoById(id, updatedTodo) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 $id 입니다.")

    @DeleteMapping("/{id}")
    fun deleteTodoById(@PathVariable @Min(MIN_AVAILABLE_ID) id: Long): ResponseEntity<Void> {
        todoService.getTodoById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 $id 입니다.")
        todoService.deleteTodoById(id)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/{id}/status")
    fun changeTodoStatusById(@PathVariable @Min(MIN_AVAILABLE_ID) id: Long, @RequestBody statusUpdate: StatusUpdate): Todo =
        todoService.changeTodoStatusById(id, statusUpdate) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 $id 입니다.")
}


data class StatusUpdate(val status: TodoStatus)