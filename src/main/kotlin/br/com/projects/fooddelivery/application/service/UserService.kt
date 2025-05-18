package br.com.projects.fooddelivery.application.service

import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.application.dto.UserResponse
import br.com.projects.fooddelivery.domain.entities.User
import br.com.projects.fooddelivery.infrastructure.database.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    val userRepository: UserRepository,
) {

    fun create(userRequest: UserRequest): UserResponse {
        val user = User(
            name = userRequest.name,
            secondName = userRequest.secondName,
            email = userRequest.email,
            password = userRequest.password,
            type = userRequest.type);

        val entity = userRepository.save(user.toModel());

        user.addIdentifier(UUID.fromString(entity.id));

        return user.toResponse();
    }
}