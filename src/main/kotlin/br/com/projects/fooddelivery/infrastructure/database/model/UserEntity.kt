package br.com.projects.fooddelivery.infrastructure.database.model

import br.com.projects.fooddelivery.domain.entities.User
import br.com.projects.fooddelivery.infrastructure.enums.UserType
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "USERS")
class UserEntity(
    @Id
    @Column
    val id: String = UUID.randomUUID().toString(),

    @Column
    val name: String,

    @Column
    val secondName: String,

    @Column
    val email: String,

    @Column
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column
    val type: UserType,
) {

    fun toDomain(): User {
        return User(
            UUID.fromString(id),
            name,
            secondName,
            email,
            password,
            type
        );
    }

}