package br.com.projects.fooddelivery.domain.entities

import br.com.projects.fooddelivery.application.dto.UserResponse
import br.com.projects.fooddelivery.domain.vo.Address
import br.com.projects.fooddelivery.infrastructure.database.model.UserEntity
import br.com.projects.fooddelivery.infrastructure.enums.UserType

class User(
    private var id: String? = null,
    private var name: String,
    private var secondName: String,
    private var email: String,
    private var password: String,
    private var type: UserType,
    private var isActive: Boolean = false,
    private var address: Address?
) {

    init {
        this.validation();
    }

    private fun validation(): Boolean {
        val condition = name.isNotBlank() &&
                secondName.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank() &&
                name.isNotBlank() &&
                UserType.isValid(type.name);

        if (!condition) {
            throw IllegalArgumentException("Invalid param")
        }

        return true;
    }

    fun active() {
        this.isActive = true;
    }

    fun deactivate() {
        this.isActive = false;
    }

    fun addIdentifier(id: String) {
        this.id = id;
    }

    fun toResponse(): UserResponse {
        val address = address?.toResponse();
        return UserResponse(id, name, secondName, type, isActive, address);
    }

    fun toModel(): UserEntity {
        val userEntity = UserEntity(
            id = null,
            name = name,
            secondName = secondName,
            email = email,
            password = password,
            type= type,
            activate = isActive,
            address = null,
        );

        val address = address?.toEntity(userEntity);

        userEntity.changeAddress(address);

        return userEntity;
    }
}