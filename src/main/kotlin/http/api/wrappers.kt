package http.api

data class ResponseWrapper(val error: ErrorResponse? = null,
                           val data: Any? = null)

data class ErrorResponse(val code: Int,
                         val message: String)