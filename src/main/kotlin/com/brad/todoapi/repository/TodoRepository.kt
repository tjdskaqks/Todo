package com.brad.todoapi.repository

import com.brad.todoapi.models.Todo
import com.brad.todoapi.models.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findAllByUser(user: Users): List<Todo>
}