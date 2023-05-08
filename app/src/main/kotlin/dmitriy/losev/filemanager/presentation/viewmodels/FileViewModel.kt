package dmitriy.losev.filemanager.presentation.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.exception.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.filemanager.core.file.SortedOrder
import dmitriy.losev.filemanager.core.file.SortedType
import dmitriy.losev.filemanager.domain.models.FileModel
import dmitriy.losev.filemanager.domain.usecases.FileUseCases
import dmitriy.losev.filemanager.presentation.navigation.Screens
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

class FileViewModel(
    errorHandler: ErrorHandler,
    private val fileUseCases: FileUseCases
) : BaseViewModel(errorHandler = errorHandler) {

    private val _files: MutableStateFlow<List<FileModel>> = MutableStateFlow(value = emptyList())
    private val _sortedType: MutableStateFlow<SortedType> =
        MutableStateFlow(value = SortedType.STRAIGHT)
    private val _sortedOrder: MutableStateFlow<SortedOrder> =
        MutableStateFlow(value = SortedOrder.ALPHABET)

    val files: StateFlow<List<FileModel>> = _files
    val sortedType: StateFlow<SortedType> = _sortedType
    val sortedOrder: StateFlow<SortedOrder> = _sortedOrder

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun loadingFiles(path: String) {
        fileUseCases.getFiles(path = path).resultDefaultHandle { files ->
            _files.value = files
        }
    }

    fun onChangePath(newPath: String, navController: NavController) {
        navController.navigate(Screens.FileScreen.createRoute(path = newPath))
    }

    fun onSortedOrderChanged(sortedOrder: SortedOrder) {
        if (sortedOrder == _sortedOrder.value) {
            _sortedType.value = SortedType.reverseType(_sortedType.value)
        } else {
            _sortedOrder.value = sortedOrder
        }
        sortedFiles()
    }

    fun sortedFiles() = runOnBackground {
        _files.value = fileUseCases.sortedFiles(
            files = _files.value,
            sortedOrder = _sortedOrder.value,
            sortedType = _sortedType.value
        )
    }
}