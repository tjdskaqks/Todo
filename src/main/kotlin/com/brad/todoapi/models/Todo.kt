package com.brad.todoapi.models

import jakarta.persistence.* // ktlint-disable no-wildcard-imports
import jakarta.validation.constraints.NotBlank
import java.time.LocalDateTime

@Entity
class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    @field:NotBlank(message = "title is required")
    var title: String,
    @Column
    var content: String? = null,
    @Enumerated(EnumType.STRING)
    var status: TodoStatus = TodoStatus.INCOMPLETE,
    @Column(name = "complete_at")
    var completeAt: LocalDateTime? = null,
    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Column(name = "update_at")
    var updateAt: LocalDateTime? = null
)

enum class TodoStatus {
    INCOMPLETE,
    COMPLETE
}
