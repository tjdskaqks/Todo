package com.brad.todoapi.advice

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.server.ResponseStatusException

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler(ResponseStatusException::class)
    fun handleResponseStatusException(e: ResponseStatusException): ResponseEntity<com.brad.todoapi.exception.ErrorResponse> {
        val errorResponse = com.brad.todoapi.exception.ErrorResponse(e.status.value(), e.reason ?: "An error occurred.")
        return ResponseEntity(errorResponse, e.status)
    }

    // 엔티티에서 @Valid 어노테이션을 사용하면, 이 메소드가 실행된다.
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): ResponseEntity<Map<String, String>> {
        val errors = mutableMapOf<String, String>()

        exception.bindingResult.fieldErrors.forEach { error ->
            val fieldName = error.field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Unknown error"
        }

        return ResponseEntity(errors, HttpStatus.BAD_REQUEST)
    }

    // 엔티티에서 @UniqueUsername 어노테이션을 사용하면, 이 메소드가 실행된다.
    @ExceptionHandler(DataIntegrityViolationException::class)
    fun handleDataIntegrityViolationExceptions(exception: DataIntegrityViolationException): ResponseEntity<String> {
        return if (exception.cause is java.sql.SQLIntegrityConstraintViolationException) {
            ResponseEntity("Username already exists.", HttpStatus.CONFLICT)
        } else {
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}