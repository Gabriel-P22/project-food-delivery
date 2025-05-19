package br.com.projects.fooddelivery.domain.service

import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.application.dto.UserResponse
import br.com.projects.fooddelivery.domain.entities.User
import br.com.projects.fooddelivery.infrastructure.database.model.UserEntity
import br.com.projects.fooddelivery.infrastructure.database.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun create(userRequest: UserRequest): UserResponse {
        val user = User(
            name = userRequest.name,
            secondName = userRequest.secondName,
            email = userRequest.email,
            password = userRequest.password,
            type = userRequest.type);

        val entity = userRepository.save(user.toModel());

        entity.id?.let { user.addIdentifier(it) };

        return user.toResponse();
    }

    fun findById(id: String): UserResponse {
        val entity = this.findUserById(id);
        return entity.toDomain().toResponse();
    }

    fun update(id: String, userRequest: UserRequest): UserResponse {
        val entity = this.findUserById(id).merger(userRequest);
        return userRepository.save(entity).toDomain().toResponse();
    }

    fun delete(id: String) {
        userRepository.delete(findUserById(id));
    }

    private fun findUserById(id: String): UserEntity {
        return userRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") };
    }
}