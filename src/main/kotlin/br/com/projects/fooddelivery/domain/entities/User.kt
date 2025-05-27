package br.com.projects.fooddelivery.domain.entities

import br.com.projects.fooddelivery.application.dto.UserResponse
import br.com.projects.fooddelivery.domain.vo.Address
import br.com.projects.fooddelivery.infrastructure.database.model.UserEntity
import br.com.projects.fooddelivery.infrastructure.database.model.WalletEntity
import br.com.projects.fooddelivery.infrastructure.enums.UserType

class User(
    private var id: String? = null,
    private var name: String,
    private var secondName: String,
    private var email: String,
    private var password: String,
    private var type: UserType,
    private var isActive: Boolean = false,
    private var address: Address?,
    private var wallet: Wallet?
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

    fun getWallet() = this.wallet;

    fun addIdentifier(id: String?) {
        this.id = id;
    }

    fun toResponse(): UserResponse {
        val address = address?.toResponse();
        val wallet = wallet?.toResponse();

        return UserResponse(id, name, secondName, type, isActive, address, wallet);
    }

    fun addWallet(wallet: Wallet?) {
        this.wallet = wallet;
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
            wallet = null
        );

        val address = address?.toEntity();

        userEntity.changeAddress(address);

        return userEntity;
    }
}