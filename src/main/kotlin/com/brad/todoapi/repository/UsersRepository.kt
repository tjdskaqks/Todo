package com.brad.todoapi.repository

import com.brad.todoapi.models.UserRoles
import com.brad.todoapi.models.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository : JpaRepository<Users, Long> {
    fun findByUsername(username: String): Users?
    fun findByRole(role: UserRoles): List<Users>
}