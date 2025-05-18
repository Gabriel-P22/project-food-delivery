package br.com.projects.fooddelivery.application.dto

import br.com.projects.fooddelivery.infrastructure.enums.UserType
import java.util.UUID

data class UserResponse(
    val id: UUID?,
    val name: String,
    val secondName: String,
    val type: UserType
)
