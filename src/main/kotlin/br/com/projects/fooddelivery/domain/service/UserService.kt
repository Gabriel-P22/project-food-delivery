package br.com.projects.fooddelivery.domain.service

import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.application.dto.UserResponse
import br.com.projects.fooddelivery.domain.entities.User
import br.com.projects.fooddelivery.domain.vo.Address
import br.com.projects.fooddelivery.infrastructure.database.model.UserEntity
import br.com.projects.fooddelivery.infrastructure.database.repository.UserRepository
import br.com.projects.fooddelivery.infrastructure.exception.ConflictException
import br.com.projects.fooddelivery.infrastructure.exception.NotFoundException
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun create(userRequest: UserRequest): UserResponse {

        if (userRepository.existsByEmail(userRequest.email)) {
            throw ConflictException("Use another email");
        }

        val address = Address(
            userRequest.address.street,
            userRequest.address.number,
            userRequest.address.city,
            userRequest.address.state,
            userRequest.address.zipcode,
            userRequest.address.type
        )

        val user = User(
            name = userRequest.name,
            secondName = userRequest.secondName,
            email = userRequest.email,
            password = userRequest.password,
            type = userRequest.type,
            address = address);

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
        return userRepository.findById(id).orElseThrow { NotFoundException("User not found") };
    }
}