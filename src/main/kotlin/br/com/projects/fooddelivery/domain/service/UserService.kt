package br.com.projects.fooddelivery.domain.service

import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.application.dto.UserResponse
import br.com.projects.fooddelivery.domain.entities.User
import br.com.projects.fooddelivery.domain.entities.Wallet
import br.com.projects.fooddelivery.domain.vo.Address
import br.com.projects.fooddelivery.infrastructure.database.model.AddressEntity
import br.com.projects.fooddelivery.infrastructure.database.model.UserEntity
import br.com.projects.fooddelivery.infrastructure.database.model.WalletEntity
import br.com.projects.fooddelivery.infrastructure.database.repository.UserRepository
import br.com.projects.fooddelivery.infrastructure.enums.AddressType
import br.com.projects.fooddelivery.infrastructure.exception.ConflictException
import br.com.projects.fooddelivery.infrastructure.exception.NotFoundException
import org.springframework.stereotype.Service
import java.util.Objects

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    fun create(userRequest: UserRequest): UserResponse {

        if (userRepository.existsByEmail(userRequest.email)) {
            throw ConflictException("Use another email");
        }

        val user = User(userRequest);

        val entity = userRepository.save(user.toModel());

        return entity.toDomain().toResponse();
    }

    fun findById(id: String): UserResponse {
        return  this.findUserById(id).toDomain().toResponse();
    }

    fun update(id: String, userRequest: UserRequest): UserResponse {
        val entity = this.findUserById(id);

        userRequest.apply {
            entity.name = name;
            entity.secondName = secondName
            entity.email = email
            entity.password = password
            entity.type = type
            entity.address = Address(
                userRequest.address
            ).toEntity();
        }

        userRepository.save(entity);

        return entity.toDomain().toResponse();
    }

    fun delete(id: String) {
        userRepository.delete(findUserById(id));
    }

    private fun findUserById(id: String): UserEntity {
        return userRepository.findById(id).orElseThrow { NotFoundException("User not found") };
    }
}