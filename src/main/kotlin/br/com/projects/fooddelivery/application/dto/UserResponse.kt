package br.com.projects.fooddelivery.application.dto

import br.com.projects.fooddelivery.infrastructure.enums.UserType

data class UserResponse(
    val id: String,
    val name: String,
    val secondName: String,
    val type: UserType
)
