package com.brad.todoapi.advice

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(e: ResponseStatusException): ResponseEntity<com.brad.todoapi.exception.ErrorResponse> {
        val errorResponse = com.brad.todoapi.exception.ErrorResponse(e.statusCode.value(), e.reason ?: "An error occurred.")
        return ResponseEntity(errorResponse, e.statusCode)
    }
}