package br.com.projects.fooddelivery.domain.entities

import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.application.dto.UserResponse
import br.com.projects.fooddelivery.domain.vo.Address
import br.com.projects.fooddelivery.infrastructure.database.model.UserEntity
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

    constructor(request: UserRequest) : this(
        id = null,
        name = request.name,
        secondName = request.secondName,
        email = request.email,
        password = request.password,
        type = request.type,
        isActive = false,
        address = Address(request.address),
        wallet = Wallet(null)
    )

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

    fun toResponse(): UserResponse {
        val address = address?.toResponse();
        val wallet = wallet?.toResponse();

        return UserResponse(id, name, secondName, type, isActive, address, wallet);
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
        val wallet = wallet?.toEntity();

        userEntity.changeAddress(address);
        userEntity.changeWallet(wallet);

        return userEntity;
    }
}