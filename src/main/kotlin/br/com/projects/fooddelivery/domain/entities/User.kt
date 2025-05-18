package br.com.projects.fooddelivery.domain.entities

import br.com.projects.fooddelivery.application.dto.UserResponse
import br.com.projects.fooddelivery.infrastructure.database.model.UserEntity
import br.com.projects.fooddelivery.infrastructure.enums.UserType
import java.util.UUID

class User(
    private var id: UUID? = null,
    private var name: String,
    private var secondName: String,
    private var email: String,
    private var password: String,
    private var type: UserType
) {

    init {
        if (!validation()) {
            throw IllegalArgumentException("Invalid fields");
        }
    }

    private fun validation(): Boolean {
        return name.isNotBlank() &&
                secondName.isNotBlank() &&
                email.isNotBlank() &&
                password.isNotBlank() &&
                name.isNotBlank() &&
                UserType.isValid(type.name)
    }

    fun addIdentifier(id: UUID) {
        this.id = id;
    }

    fun toResponse(): UserResponse {
        return UserResponse(id, name, secondName, type);
    }

    fun toModel(): UserEntity {
        return UserEntity(
            name = name,
            secondName = secondName,
            email = email,
            password = password,
            type= type
        );
    }

}