package com.brad.todoapi.services

import com.brad.todoapi.helper.MathConstant
import com.brad.todoapi.models.UserRoles
import com.brad.todoapi.models.Users
import com.brad.todoapi.repository.UsersRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.validation.constraints.Min

@Service
class UsersService(private val usersRepository: UsersRepository) {

    fun createUser(user: Users): Users? {
        getUserByUsername(user.username) ?: return usersRepository.save(user)
        return null
    }

    fun getUserById(@Min(MathConstant.MIN_AVAILABLE_ID) id: Long): Users? = usersRepository.findByIdOrNull(id)

    fun getUserByUsername(username: String): Users? = usersRepository.findByUsername(username)

    fun getAllUsers(): List<Users> = usersRepository.findAll()

    fun getUsersByRole(role: UserRoles): List<Users> = usersRepository.findByRole(role)

    fun updateUserById(@Min(MathConstant.MIN_AVAILABLE_ID) id: Long, updatedUser: Users): Users? {
        val existingUser = getUserById(id) ?: return null

        if (updatedUser.username != existingUser.username) {
            usersRepository.findByUsername(updatedUser.username)?.let {
                throw IllegalArgumentException("Username already exists")
            }
        }

        existingUser.apply {
            username = updatedUser.username
            role = updatedUser.role.takeIf { it != role } ?: role
            updateAt = LocalDateTime.now()
        }

        return usersRepository.save(existingUser)
    }


    fun deleteUserById(@Min(MathConstant.MIN_AVAILABLE_ID) id: Long) = usersRepository.deleteById(id)
}