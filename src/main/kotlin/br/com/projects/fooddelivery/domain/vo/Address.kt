package br.com.projects.fooddelivery.domain.vo

import br.com.projects.fooddelivery.application.dto.AddressResponse
import br.com.projects.fooddelivery.infrastructure.database.model.AddressEntity
import br.com.projects.fooddelivery.infrastructure.database.model.UserEntity
import br.com.projects.fooddelivery.infrastructure.enums.AddressType

class Address(
    private var street: String,
    private var number: String,
    private var city: String,
    private var state: String,
    private var zipcode: String,
    private var type: AddressType
) {

    init {
        this.validation();
    }

    private fun validation(): Boolean {
        val condition =
                street.isNotBlank() &&
                number.isNotBlank() &&
                city.isNotBlank() &&
                state.isNotBlank() &&
                zipcode.isNotBlank() &&
                AddressType.isValid(type.name);

        if (!condition) {
            throw IllegalArgumentException("Invalid param")
        }

        return true;
    }

    fun toEntity(): AddressEntity {
        return AddressEntity(
            null,
            street,
            number,
            city,
            state,
            zipcode,
            type,
        );
    }

    fun toResponse(): AddressResponse {
        return AddressResponse(
            street,
            number,
            city,
            state,
            zipcode,
            type
        )
    }

}