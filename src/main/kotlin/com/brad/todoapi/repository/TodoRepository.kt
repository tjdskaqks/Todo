package com.brad.todoapi.repository

import com.brad.todoapi.models.Todo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@EnableJpaRepositories
interface TodoRepository : JpaRepository<Todo, Long>