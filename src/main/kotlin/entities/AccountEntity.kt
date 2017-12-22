package entities

import models.Account
import models.Profile
import java.time.ZonedDateTime

data class AccountEntity(val id: Long,
                         val status: Int,
                         val firstName: String?,
                         val lastName: String?,
                         val email: String?,
                         val birthDate: ZonedDateTime?,
                         val avatarUrl: String?,
                         val passwordHash: String?,
                         val emailConfirmationToken: String?,
                         val resetPasswordToken: String?,
                         val resetPasswordTokenExpiryDate: ZonedDateTime?,
                         val creationDate: ZonedDateTime,
                         val lastUpdateDate: ZonedDateTime?,
                         val lastLoginDate: ZonedDateTime?
)

fun AccountEntity.toAccount() = Account(
        id = id,
        status = status,
        email = email,
        profile = Profile(
                firstName,
                lastName,
                birthDate,
                avatarUrl
        )
)