package br.com.projects.fooddelivery.infrastructure.database.model

import br.com.projects.fooddelivery.domain.entities.Wallet
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "WALLETS")
class WalletEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private val id: String?,

    @Column(name = "user_id", nullable = false)
    private var userId: String?,

    @Column()
    public var amount: Double,
) {

    fun toDomain(): Wallet {
        return Wallet(
            id,
            userId,
            amount,
        );
    }
}