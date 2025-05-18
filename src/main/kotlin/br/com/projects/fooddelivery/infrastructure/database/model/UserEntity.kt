package br.com.projects.fooddelivery.infrastructure.database.model

import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.domain.entities.User
import br.com.projects.fooddelivery.infrastructure.enums.UserType
import jakarta.persistence.*
import java.util.*

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

    @Column
    var email: String,

    @Column
    var password: String,

    @Column
    var activate: Boolean,

    @Enumerated(EnumType.STRING)
    @Column
    var type: UserType,
) {

    fun merger(userRequest: UserRequest): UserEntity {
        userRequest.apply {
            if (name.isNotBlank()) this@UserEntity.name = name
            if (secondName.isNotBlank()) this@UserEntity.secondName = secondName
            if (email.isNotBlank()) this@UserEntity.email = email
            if (password.isNotBlank()) this@UserEntity.password = password
            if (type.name.isNotBlank()) this@UserEntity.type = type
        }

        return this;
    }

    fun toDomain(): User {
        return User(
            UUID.fromString(id),
            name,
            secondName,
            email,
            password,
            type,
            activate
        );
    }

}