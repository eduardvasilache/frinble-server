package api.exceptions

open class ApiException(override val message: String) : RuntimeException(message)

class InvalidParameterException(parameterName: String) : ApiException("Invalid parameter: $parameterName")