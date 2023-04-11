package com.brad.todoapi.controller

import com.brad.todoapi.models.Todo
import com.brad.todoapi.models.TodoStatus
import com.brad.todoapi.repository.TodoRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.util.*

class TodoControllerTest (@Autowired private val mockMvc: MockMvc) {
    /*@MockBean
    private lateinit var todoRepository: TodoRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private  lateinit var todo: Todo

    @BeforeEach
    fun setUp() {
        todo = Todo(
            id = 1L,
            title = "Test Todo",
            content = "Test Content",
            status = TodoStatus.INCOMPLETE,
            createdAt = LocalDateTime.now()
        )
    }

    @Test
    fun `createTodo - Todo 생성`() {
        // given
        `when`(todoRepository.save((todo))).thenReturn(todo)
        // when
        // then
        mockMvc.perform(
            post("/api/v1/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todo))
        )
            .andExpect(status().isCreated)
            .andExpect(jsonPath("$.title").value(todo.title))
    }

    @Test
    fun `createTodo - fail - empty title`() {
        // Given
        val invalidTodo = todo.copy(title = "")

        // When & Then
        mockMvc.perform(
            post("/api/v1/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidTodo))
        )
            .andExpect(status().isBadRequest)
    }

    @Test
    fun `getAllTodos`() {
        // Given
        `when`(todoRepository.findAll()).thenReturn(listOf(todo))

        // When & Then
        mockMvc.perform(get("/api/v1/todos"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.[0].title").value(todo.title))
    }

    @Test
    fun `getTodoById - success`() {
        // Given
        `when`(todoRepository.findById(todo.id)).thenReturn(Optional.of(todo))

        // When & Then
        mockMvc.perform(get("/api/v1/todos/${todo.id}"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value(todo.title))
    }

    @Test
    fun `getTodoById - fail - not found`() {
        // Given
        `when`(todoRepository.findById(todo.id)).thenReturn(Optional.empty())

        // When & Then
        mockMvc.perform(get("/api/v1/todos/${todo.id}"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `updateTodo - success`() {
        // Given
        val updatedTodo = todo.copy(title = "Updated Todo")
        `when`(todoRepository.findById(todo.id)).thenReturn(Optional.of(todo))
        `when`(todoRepository.save(any(Todo::class.java))).thenReturn(updatedTodo)

        // When & Then
        mockMvc.perform(
            put("/api/v1/todos/${todo.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedTodo))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.title").value(updatedTodo.title))
    }

    @Test
    fun `updateTodo - fail - not found`() {
        // Given
        `when`(todoRepository.findById(todo.id)).thenReturn(Optional.empty())

        // When & Then
        mockMvc.perform(
            put("/api/v1/todos/${todo.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todo))
        )
            .andExpect(status().isNotFound)
    }

    @Test
    fun `deleteTodo - success`() {
        // Given
        `when`(todoRepository.findById(todo.id)).thenReturn(Optional.of(todo))

        // When & Then
        mockMvc.perform(delete("/api/v1/todos/${todo.id}"))
            .andExpect(status().isNoContent)
    }

    @Test
    fun `deleteTodo - fail - not found`() {
        // Given
        `when`(todoRepository.findById(todo.id)).thenReturn(Optional.empty())

        // When & Then
        mockMvc.perform(delete("/api/v1/todos/${todo.id}"))
            .andExpect(status().isNotFound)
    }

    @Test
    fun `changeTodoStatus - success`() {
        // Given
        val statusUpdate = StatusUpdate(status = TodoStatus.COMPLETE)
        `when`(todoRepository.findById(todo.id)).thenReturn(Optional.of(todo))
        `when`(todoRepository.save(any(Todo::class.java))).thenReturn(todo.copy(status = TodoStatus.COMPLETE))

        // When & Then
        mockMvc.perform(
            put("/api/v1/todos/${todo.id}/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(statusUpdate))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.status").value(TodoStatus.COMPLETE.name))
    }

    @Test
    fun `changeTodoStatus - fail - not found`() {
        // Given
        val statusUpdate = StatusUpdate(status = TodoStatus.COMPLETE)
        `when`(todoRepository.findById(todo.id)).thenReturn(Optional.empty())

        // When & Then
        mockMvc.perform(
            put("/api/v1/todos/${todo.id}/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(statusUpdate))
        )
            .andExpect(status().isNotFound)
    }*/

}
