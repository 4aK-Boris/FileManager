package dmitriy.losev.core.core

import android.content.ActivityNotFoundException
import dmitriy.losev.core.core.exception.BaseException
import dmitriy.losev.core.core.exception.NOT_FOUND_APPLICATION
import dmitriy.losev.core.core.exception.SAFE_CALL_FAIL
import dmitriy.losev.core.core.exception.VERY_FAST_CLICKS
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.cancellation.CancellationException

open class BaseUseCase {

    suspend fun <T : Any> safeCall(call: suspend CoroutineScope.() -> T): Result<T> = try {
        coroutineScope {
            Result.Success(call())
        }
    } catch (baseException: BaseException) {
        Result.Error(baseException, baseException.extraErrorCode)
    } catch (ex: CancellationException) {
        Result.Error(ex, VERY_FAST_CLICKS)
    } catch (ex: ActivityNotFoundException) {
        Result.Error(ex, NOT_FOUND_APPLICATION)
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
