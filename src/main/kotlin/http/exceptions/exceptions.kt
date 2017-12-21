package http.exceptions

open class ApiException(val code: Int, override val message: String) : RuntimeException(message)

class InvalidParameterException(parameterName: String) : ApiException(1, "Missing or invalid parameter: $parameterName")

class UserNotFoundException : ApiException(2, "User not found")