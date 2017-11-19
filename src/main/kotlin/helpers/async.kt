package helpers

import io.vertx.core.AsyncResult
import io.vertx.core.Handler
import kotlin.coroutines.experimental.suspendCoroutine

inline suspend fun <T> withHandler(crossinline callback: (Handler<AsyncResult<T>>) -> Unit) = suspendCoroutine<T> { cont ->
    callback(Handler { result: AsyncResult<T> ->
        if (result.succeeded()) {
            cont.resume(result.result())
        } else {
            cont.resumeWithException(result.cause())
        }
    })
}