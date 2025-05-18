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
    private var type: UserType,
    private var isActive: Boolean = false
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
        this.validation();
        this.isActive = true;
    }

    fun deactivate() {
        this.validation();
        this.isActive = false;
    }

    fun addIdentifier(id: UUID) {
        this.validation()
        this.id = id;
    }

    fun toResponse(): UserResponse {
        return UserResponse(id, name, secondName, type, isActive);
    }

    fun toModel(): UserEntity {
        return UserEntity(
            name = name,
            secondName = secondName,
            email = email,
            password = password,
            type= type,
            activate = isActive
        );
    }
}