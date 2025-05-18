package br.com.projects.fooddelivery.domain.entities

import br.com.projects.fooddelivery.infrastructure.enums.UserType

class User(
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

}