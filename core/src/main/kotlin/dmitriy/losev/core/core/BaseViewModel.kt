package dmitriy.losev.core.core

import dmitriy.losev.core.core.exception.ErrorHandler
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel(private val errorHandler: ErrorHandler): ViewModel() {

    open val errorMap: Map<Int, String> = emptyMap()

    init {
        errorHandler.setErrorActionsMap(errorsMap = errorMap)
    }

    val errorMessage: StateFlow<String> = errorHandler.errorMessage
    val errorState: StateFlow<Boolean> = errorHandler.errorState

    val close = errorHandler::close

    suspend fun Result<Unit>.resultDefaultHandle() {
        if (this is Result.Error) errorHandler.handleError(extraErrorCode)
    }

    suspend fun <T> Result<T>.resultDefaultHandle(successBlock: suspend (T) -> Unit) {
        when (this) {
            is Result.Success -> successBlock(data)
            is Result.Error -> errorHandler.handleError(extraErrorCode)
        }
    }
}
