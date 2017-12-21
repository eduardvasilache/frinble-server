package models

data class Account(val id: Long,
                   val status: Int,
                   val firstName: String?,
                   val lastName: String?,
                   val email: String?
)

/*

data class Account(val id: Long,
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
                   val lastUpdateDateTime: ZonedDateTime?,
                   val lastLoginDateTime: ZonedDateTime?
)

*/