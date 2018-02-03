package http.api

const val ERROR_GENERIC = 1
const val ERROR_INVALID_PARAMETERS = 2
const val ERROR_INVALID_CREDENTIALS = 3
const val ERROR_USER_NOT_FOUND = 4

open class ApiException(val code: Int, override val message: String) : RuntimeException(message)

class InvalidParameterException(parameterName: String) :
    ApiException(ERROR_INVALID_PARAMETERS, "Missing or invalid parameter: $parameterName")

class UserNotFoundException
    : ApiException(ERROR_USER_NOT_FOUND, "User not found")

class InvalidCredentialsException
    : ApiException(ERROR_INVALID_CREDENTIALS, "Invalid credentials")