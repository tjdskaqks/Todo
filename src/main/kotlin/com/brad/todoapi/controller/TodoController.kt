package com.brad.todoapi.controller

import com.brad.todoapi.helper.MathConstant.MIN_AVAILABLE_ID
import com.brad.todoapi.models.*
import com.brad.todoapi.services.TodoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.http.*
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
@RequestMapping("/api/v1/todos")
class TodoController (private val todoService: TodoService) {

    @PostMapping
    fun createTodo(@Valid @RequestBody todo: Todo): Todo = todoService.createTodo(todo)

    @GetMapping
    fun getAllTodos(): List<Todo> = todoService.getAllTodos()

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