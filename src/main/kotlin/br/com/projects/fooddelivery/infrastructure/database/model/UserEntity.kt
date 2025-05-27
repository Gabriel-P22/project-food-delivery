package br.com.projects.fooddelivery.infrastructure.database.model

import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.domain.entities.User
import br.com.projects.fooddelivery.infrastructure.enums.UserType
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "USERS")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    val id: String?,

    @Column
    var name: String,

    @Column
    var secondName: String,

    @Column(unique = true, nullable = false)
    var email: String,

    @Column
    var password: String,

    @Column
    var activate: Boolean,

    @Enumerated(EnumType.STRING)
    @Column
    var type: UserType,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "address_id")
    var address: AddressEntity?,

    @OneToOne(cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    var wallet: WalletEntity?,
) {

    fun merger(userRequest: UserRequest): UserEntity {
        userRequest.apply {
            if (name.isNotBlank()) this@UserEntity.name = name
            if (secondName.isNotBlank()) this@UserEntity.secondName = secondName
            if (email.isNotBlank()) this@UserEntity.email = email
            if (password.isNotBlank()) this@UserEntity.password = password
            if (type.name.isNotBlank()) this@UserEntity.type = type
            this@UserEntity.address = AddressEntity(
                null,
                address.street,
                address.number,
                address.city,
                address.state,
                address.zipcode,
                address.type,
            )
        }

        return this;
    }

    fun changeAddress(address: AddressEntity?) {
        this.address = address;
    }

    fun changeWallet(wallet: WalletEntity?) {
        this.wallet = wallet;
    }

    fun toDomain(): User {
        return User(
            id,
            name,
            secondName,
            email,
            password,
            type,
            activate,
            address?.toDomain(),
            wallet?.toDomain()
            );
    }
}