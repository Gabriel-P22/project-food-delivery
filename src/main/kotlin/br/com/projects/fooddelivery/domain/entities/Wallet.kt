package br.com.projects.fooddelivery.domain.entities

import br.com.projects.fooddelivery.application.dto.WalletResponse
import br.com.projects.fooddelivery.infrastructure.database.model.WalletEntity
import br.com.projects.fooddelivery.infrastructure.exception.DomainValidationException

class Wallet(
    private val id: String?,
    private var userId: String?,
    private var amount: Double = 0.0
) {

    init {
        this.validate()
    }

    fun validate(): Boolean {
        val condition: Boolean = amount < 0.0

        if (condition) {
            throw DomainValidationException("invalid validation")
        }

        return true;
    }

    fun toEntity(): WalletEntity {
        return WalletEntity(
            id,
            userId,
            amount
        )
    }

    fun toResponse(): WalletResponse {
        return WalletResponse(
            this.amount
        );
    }
}