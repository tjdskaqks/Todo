package com.brad.todoapi.models

import javax.persistence.*
import java.time.LocalDateTime

@Entity
class Users (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,
    @Column(nullable = false)
    val username: String,
    @Enumerated(EnumType.STRING)
    val role: UserRoles = UserRoles.USER,
    @Column
    val createAt: LocalDateTime = LocalDateTime.now(),
    @Column
    val updateAt: LocalDateTime? = null
)

enum class UserRoles{
    ADMIN,
    USER
}