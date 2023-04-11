package com.brad.todoapi.services

import com.brad.todoapi.helper.MathConstant
import com.brad.todoapi.models.UserRoles
import com.brad.todoapi.models.Users
import com.brad.todoapi.repository.UsersRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.validation.constraints.Min

@Service
class UsersService(private val usersRepository: UsersRepository) {

    fun createUser(user: Users): Users? {
        val existingUser = getUserByUsername(user.username) ?: return usersRepository.save(user)
        return null
    }

    fun getUserById(@Min(MathConstant.MIN_AVAILABLE_ID) id: Long): Users? = usersRepository.findByIdOrNull(id)

    fun getUserByUsername(username: String): Users? = usersRepository.findByUsername(username)

    fun getAllUsers(): List<Users> = usersRepository.findAll()

    fun getUsersByRole(role: UserRoles): List<Users> = usersRepository.findByRole(role)

    fun updateUserById(@Min(MathConstant.MIN_AVAILABLE_ID) id: Long, updatedUser: Users): Users? {
        val existingUser = getUserById(id) ?: return null

        existingUser.apply {
            username = updatedUser.username.takeIf { it != username } ?: username
            role = updatedUser.role.takeIf { it != role } ?: role
        }

        return usersRepository.save(existingUser)
    }

    fun deleteUserById(@Min(MathConstant.MIN_AVAILABLE_ID) id: Long) = usersRepository.deleteById(id)
}