package br.com.projects.fooddelivery.application.controller

import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.application.dto.UserResponse
import br.com.projects.fooddelivery.domain.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun create(@RequestBody user: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/{id}")
    fun create(@PathVariable id: String): ResponseEntity<UserResponse> {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: String, @RequestBody userRequest: UserRequest): ResponseEntity<UserResponse> {
        return ResponseEntity.ok().body(userService.update(id, userRequest));
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: String): ResponseEntity<Void> {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}