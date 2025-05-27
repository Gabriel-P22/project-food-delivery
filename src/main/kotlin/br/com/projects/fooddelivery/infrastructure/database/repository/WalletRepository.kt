package br.com.projects.fooddelivery.infrastructure.database.repository

import br.com.projects.fooddelivery.infrastructure.database.model.WalletEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WalletRepository: JpaRepository<WalletEntity, String> {
}