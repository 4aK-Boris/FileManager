package dmitriy.losev.filemanager.presentation.viewmodels

import android.app.Application
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.exception.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.filemanager.core.file.SortedOrder
import dmitriy.losev.filemanager.core.file.SortedType
import dmitriy.losev.filemanager.core.work.HashFilesWork
import dmitriy.losev.filemanager.domain.models.FileModel
import dmitriy.losev.filemanager.domain.usecases.FileUseCases
import dmitriy.losev.filemanager.presentation.navigation.Screens
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.io.File

class FileViewModel(
    errorHandler: ErrorHandler,
    private val application: Application,
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

    private val workManager = WorkManager.getInstance(application)

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun loadingFiles(path: String) {
        fileUseCases.getFiles(path = path).resultDefaultHandle { files ->
            _files.value = files
        }
    }

    fun createHashFiles(path: String) {
        val hashFilesWorkRequest =
            OneTimeWorkRequestBuilder<HashFilesWork>()
                .setInputData(workDataOf(HashFilesWork.PATH_KEY to path))
                .build()
        workManager.enqueue(hashFilesWorkRequest)
    }

    fun onChangePath(newPath: String, navController: NavController) {
        navController.navigate(Screens.FileScreen.createRoute(path = newPath))
    }

    fun onSortedOrderChanged(sortedOrder: SortedOrder) = runOnBackground {
        if (sortedOrder == _sortedOrder.value) {
            _sortedType.value = SortedType.reverseType(_sortedType.value)
        } else {
            _sortedOrder.value = sortedOrder
        }
        sortedFiles()
    }

    suspend fun checkChangingFile() {
        fileUseCases.checkChangingFile(files = _files.value).resultDefaultHandle { files ->
            _files.value = files
        }
    }

    suspend fun sortedFiles() {
        fileUseCases.sortingFiles(
            files = _files.value,
            sortedOrder = _sortedOrder.value,
            sortedType = _sortedType.value
        ).resultDefaultHandle { files ->
            _files.value = files
        }
    }

    fun openFile(path: String) {
        with(application) {
            val intent = Intent(Intent.ACTION_VIEW)
            val uri = FileProvider.getUriForFile(
                this,
                applicationContext.packageName + ".provider",
                File(path)
            )
            intent.setDataAndType(uri, contentResolver.getType(uri))
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    fun sharedFile(path: String) {
        with(application) {
            val intent = Intent(Intent.ACTION_SEND)
            val uri = FileProvider.getUriForFile(
                this,
                applicationContext.packageName + ".provider",
                File(path)
            )
            intent.setDataAndType(uri, contentResolver.getType(uri))
            intent.putExtra(Intent.EXTRA_TEXT, "Файл взят из FileManager")
            intent.putExtra(Intent.EXTRA_STREAM, uri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivity(Intent.createChooser(intent, "Поделиться файлом").apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            })
        }
    }
}