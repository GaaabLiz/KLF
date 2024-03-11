package io.github.gaaabliz.kliz.common.base

sealed class Operation <T> (
    data : T? = null,
    errorMessage:String? = null,
)  {

    companion object {

        fun <T> isOk(operation : Operation<T>) : Boolean {
            return when(operation) {
                is Success -> true
                is Error -> false
            }
        }

        fun <T> exec(
            operation : Operation<T>,
            onSuccess : (T) -> Unit,
            onError : (String?) -> Unit,
        ) {
            return when(operation) {
                is Success -> onSuccess(operation.data)
                is Error -> onError(operation.errorMessage)
            }
        }

        fun <OI, OO> execWithReturn(
            operation : Operation<OI>,
            onSuccess : (OI) -> Operation<OO>,
            onError : (String?) -> Operation<OO>,
        ) : Operation<OO> {
            return when(operation) {
                is Success -> { return onSuccess(operation.data) }
                is Error -> onError(operation.errorMessage)
            }
        }



        fun <T> getSuccessDataFrom(operation: Operation<T>, onError : (String?) -> Unit,) : T? {
            return when(operation) {
                is Success -> operation.data
                is Error -> {
                    onError(operation.errorMessage)
                    null
                }
            }
        }

        suspend fun <T> exec(
            operation : Operation<T>,
            onSuspendSuccess : suspend (T) -> Unit,
            onError : (String?) -> Unit,
        ) {
            return when(operation) {
                is Success -> onSuspendSuccess(operation.data)
                is Error -> onError(operation.errorMessage)
            }
        }
    }

    data class Success <T>(
        val data: T,
    ) : Operation<T>(data, null)

    data class Error <T>(
        val errorMessage: String?,
    ) : Operation<T>(null, errorMessage) {

    }
}

inline fun <T> safeCall(action: () -> Operation<T>): Operation<T> {
    return try {
        action()
    } catch (e: Exception) {
        Operation.Error(e.message ?: "An unknown Error Occurred")
    }
}

class OperationException(
    val msg:String,
    val state:Boolean? = null,
    val title:String? = null,
) : Exception(msg) {
}

