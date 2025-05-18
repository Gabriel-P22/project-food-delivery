package br.com.projects.fooddelivery.application.controller

import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.application.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController {

    @PostMapping
    fun create(@RequestBody user: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    fun create(@PathVariable id: String): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(null);
    }

}