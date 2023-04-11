package com.brad.todoapi.models

import javax.persistence.*
import java.time.LocalDateTime
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity
class Users (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false, unique = true)
    @field:NotBlank(message = "Username is required")
    @field:Pattern(regexp = "^[a-zA-Z0-9_]*$", message = "Username must contain only alphanumeric characters and underscores")
    var username: String,
    @Enumerated(EnumType.STRING)
    var role: UserRoles = UserRoles.USER,
    @Column
    val createAt: LocalDateTime = LocalDateTime.now(),
    @Column
    val updateAt: LocalDateTime? = null,
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val todos: MutableList<Todo> = mutableListOf()
)

enum class UserRoles{
    ADMIN,
    USER
}