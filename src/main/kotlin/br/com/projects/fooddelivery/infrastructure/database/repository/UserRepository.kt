package br.com.projects.fooddelivery.infrastructure.database.repository

import br.com.projects.fooddelivery.infrastructure.database.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<UserEntity, String> {
}