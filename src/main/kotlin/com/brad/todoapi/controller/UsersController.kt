package com.brad.todoapi.controller

import com.brad.todoapi.helper.MathConstant
import com.brad.todoapi.models.UserRoles
import com.brad.todoapi.models.Users
import com.brad.todoapi.services.UsersService
import org.intellij.lang.annotations.RegExp
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
@RequestMapping("/api/v1/users")
class UsersController(private val usersService: UsersService) {

    @PostMapping
    fun createUser(@Valid @RequestBody user: Users) : Users? = usersService.createUser(user) ?: throw ResponseStatusException(
        HttpStatus.CONFLICT, "User already exists")

    @GetMapping
    fun getUserByUsername(@RequestParam username: String) = usersService.getUserByUsername(username) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

    @GetMapping("/all")
    fun getAllUsers() = usersService.getAllUsers()

    @GetMapping("/{id}")
    fun getUserById(@Min(MathConstant.MIN_AVAILABLE_ID) @RequestParam id: Long) : Users? = usersService.getUserById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")

    @GetMapping("/role/{role}")
    fun getUsersByRole(@PathVariable role: UserRoles): List<Users> = usersService.getUsersByRole(role)

    @DeleteMapping
    fun deleteUserById(@Min(MathConstant.MIN_AVAILABLE_ID) @RequestParam id: Long) = usersService.deleteUserById(id)

    @PutMapping("/{id}")
    fun updateUserById(@Min(MathConstant.MIN_AVAILABLE_ID)  @RequestParam id: Long, @RequestParam updatedUser: Users)
            = usersService.updateUserById(id, updatedUser)
}