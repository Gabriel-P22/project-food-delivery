package br.com.projects.fooddelivery.infrastructure.enums

enum class UserType {
    ADMIN,
    CUSTOMER,
    BUSINESS;

    companion object {
        fun isValid(type: String): Boolean {
            return entries.any() { it.name.equals(type, ignoreCase = true) }
        }
    }
}