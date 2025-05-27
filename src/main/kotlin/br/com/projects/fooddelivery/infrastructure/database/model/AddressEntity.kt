package br.com.projects.fooddelivery.infrastructure.database.model


import br.com.projects.fooddelivery.application.dto.AddressRequest
import br.com.projects.fooddelivery.application.dto.UserRequest
import br.com.projects.fooddelivery.domain.vo.Address
import br.com.projects.fooddelivery.infrastructure.enums.AddressType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "ADDRESS")
class AddressEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private val id: String?,

    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private var user: UserEntity?,

    @Column
    private var street: String,

    @Column(name = "address_number")
    private var number: String,

    @Column
    private var city: String,

    @Column
    private var state: String,

    @Column
    private var zipcode: String,

    @Column
    @Enumerated(EnumType.STRING)
    private var type: AddressType,
) {

    fun toDomain(): Address {
        return Address(
            street,
            number,
            city,
            state,
            zipcode,
            type
        );
    }
}