package com.br.desafio.backend.controller

import com.br.desafio.backend.dto.UserDTO
import com.br.desafio.backend.model.User
import com.br.desafio.backend.service.UserService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(

    @Autowired
    val userService: UserService
) {

    @PostMapping("/new")
    @Operation(summary = "Create User", description = "API new User")
    fun newUser(@RequestBody @Valid user: UserDTO): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.newUser(user))
    }

    @GetMapping
    @Operation(summary = "Find all Users")
    fun findAllUsers(): ResponseEntity<List<User>> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAllUsers())
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "find user by id")
    fun findById(@PathVariable id: Long): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @GetMapping("/cpf/{cpf}")
    @Operation(summary = "Find User by CPF")
    fun findByCpf(@PathVariable cpf: String): ResponseEntity<User> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByCpf(cpf))
    }

}