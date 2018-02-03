package models

data class Account(
    val id: Long,
    val status: Int,
    val email: String?,
    val profile: Profile
)