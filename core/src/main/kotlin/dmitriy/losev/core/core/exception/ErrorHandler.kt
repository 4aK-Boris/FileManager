package dmitriy.losev.core.core.exception

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ErrorHandler {

    private val _errorMessage: MutableStateFlow<String> = MutableStateFlow(value = "")
    private val _errorState: MutableStateFlow<Boolean> = MutableStateFlow(value = false)

    val errorMessage: StateFlow<String> = _errorMessage
    val errorState: StateFlow<Boolean> = _errorState

    fun close() {
        _errorState.value = false
    }

    private fun showError(message: String) {
        _errorState.value = true
        _errorMessage.value = message
    }

    private val errorsMap: MutableMap<Int, () -> Unit> by lazy { initErrorMap() }

    fun setErrorActionsMap(errorsMap: Map<Int, String>) {
        this.errorsMap.putAll(convert(errorsMap = errorsMap))
    }

    private fun convert(errorsMap: Map<Int, String>): Iterable<Pair<Int, () -> Unit>> {
        return errorsMap.map { (key, value) -> key to { showError(message = value) } }
    }

    private fun initErrorMap(): MutableMap<Int, () -> Unit> = mutableMapOf(
            VERY_FAST_CLICKS to { showError("Слишком быстрые нажатия. Помедленее!") },
            NOT_FOUND_APPLICATION to { showError("Нет приложения для открытия этого файла!") }
    )

    fun handleError(errorId: Int) {
        errorsMap[errorId]?.invoke() ?: showError("Неизвестная ошибка: $errorId")
    }
}