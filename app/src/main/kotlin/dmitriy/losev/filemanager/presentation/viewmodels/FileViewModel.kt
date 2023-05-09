package dmitriy.losev.filemanager.presentation.viewmodels

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import dmitriy.losev.core.core.BaseViewModel
import dmitriy.losev.core.core.exception.ErrorHandler
import dmitriy.losev.core.core.runOnBackground
import dmitriy.losev.filemanager.core.file.FileExtension
import dmitriy.losev.filemanager.core.file.SortedOrder
import dmitriy.losev.filemanager.core.file.SortedType
import dmitriy.losev.filemanager.core.work.HashFilesWork
import dmitriy.losev.filemanager.domain.models.FileModel
import dmitriy.losev.filemanager.domain.usecases.FileUseCases
import dmitriy.losev.filemanager.domain.usecases.IntentUseCases
import dmitriy.losev.filemanager.presentation.navigation.Screens
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class FileViewModel(
        errorHandler: ErrorHandler,
        application: Application,
        private val fileUseCases: FileUseCases,
        private val intentUseCases: IntentUseCases
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

    fun onClickOnItem(fileModel: FileModel, navController: NavController) = runOnBackground {
        if (fileModel.extension !== FileExtension.FOLDER) {
            intentUseCases.openFile(fileModel.path).resultDefaultHandle()
        } else {
            withContext(Dispatchers.Main) {
                onChangePath(
                        newPath = fileModel.path,
                        navController = navController
                )
            }
        }
    }

    fun onLongClickOnItem(fileModel: FileModel) = runOnBackground {
        if (fileModel.extension !== FileExtension.FOLDER) {
            intentUseCases.sharedFile(fileModel.path).resultDefaultHandle()
        }
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
        sortingFiles()
    }

    suspend fun checkChangingFile() {
        fileUseCases.checkChangingFile(files = _files.value).resultDefaultHandle { files ->
            _files.value = files
        }
    }

    suspend fun sortingFiles() {
        fileUseCases.sortingFiles(
                files = _files.value,
                sortedOrder = _sortedOrder.value,
                sortedType = _sortedType.value
        ).resultDefaultHandle { files ->
            _files.value = files
        }
    }

//    private fun openFile(path: String) {
//        with(application) {
//            val intent = createFileIntent(path = path, action = Intent.ACTION_VIEW)
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            startActivity(intent)
//        }
//    }
//
//    private fun sharedFile(path: String) {
//        with(application) {
//            val intent = createFileIntent(path = path, action = Intent.ACTION_SEND)
//            intent.putExtra(Intent.EXTRA_TEXT, "Файл взят из FileManager")
//            intent.putExtra(Intent.EXTRA_STREAM, intent.data)
//            startActivity(Intent.createChooser(intent, "Поделиться файлом").apply {
//                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//            })
//        }
//    }
//
//    private fun Application.createFileIntent(path: String, action: String): Intent {
//        val intent = Intent(action)
//        val uri = FileProvider.getUriForFile(
//                this,
//                applicationContext.packageName + ".provider",
//                File(path)
//        )
//        intent.setDataAndType(uri, contentResolver.getType(uri))
//        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
//        return intent
//    }
}