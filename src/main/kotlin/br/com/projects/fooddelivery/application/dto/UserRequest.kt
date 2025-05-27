package br.com.projects.fooddelivery.application.dto

import br.com.projects.fooddelivery.infrastructure.enums.UserType

data class UserRequest(
    val name: String,
    val secondName: String,
    val email: String,
    val password: String,
    val type: UserType,
    val address: AddressRequest
)
