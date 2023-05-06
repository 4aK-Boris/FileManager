package dmitriy.losev.core.core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import dmitriy.losev.core.core.exception.BaseException
import dmitriy.losev.core.core.exception.SAFE_CALL_FAIL

open class BaseUseCase {

    suspend fun <T : Any> safeCall(call: suspend CoroutineScope.() -> T): Result<T> = try {
        coroutineScope {
            Result.Success(call())
        }
    } catch (baseException: BaseException) {
        baseException.printStackTrace()
        Result.Error(baseException, baseException.extraErrorCode)
    } catch (ex: Exception) {
        ex.printStackTrace()
        Result.Error(ex, SAFE_CALL_FAIL)
    }

    suspend fun <T : Any, R : Any> safeCallWithError(
        call: suspend CoroutineScope.() -> T,
        onError: suspend CoroutineScope.() -> T
    ): Result<T> = try {
        coroutineScope {
            Result.Success(call())
        }
    } catch (ex: Exception) {
        safeCall {
            onError()
        }
    }
}
