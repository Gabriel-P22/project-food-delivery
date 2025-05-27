package br.com.projects.fooddelivery.application.dto

import br.com.projects.fooddelivery.infrastructure.enums.AddressType

data class AddressRequest(
    val street: String,
    val number: String,
    val city: String,
    val state: String,
    val zipcode: String,
    val type: AddressType
)