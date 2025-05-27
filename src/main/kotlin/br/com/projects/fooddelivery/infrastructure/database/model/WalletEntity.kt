package br.com.projects.fooddelivery.infrastructure.database.model

import br.com.projects.fooddelivery.domain.entities.Wallet
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table

@Entity
@Table(name = "WALLETS")
class WalletEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private val id: String?,

    @OneToOne(mappedBy = "wallet", fetch = FetchType.LAZY)
    private var user: UserEntity?,

    @Column()
    private var amount: Double,
) {

    fun toDomain(): Wallet {
        return Wallet(
            id,
            amount,
        );
    }
}