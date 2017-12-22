package models

import java.time.ZonedDateTime

data class Profile(val firstName: String?,
                   val lastName: String?,
                   val birthDate: ZonedDateTime?,
                   val avatarUrl: String?)