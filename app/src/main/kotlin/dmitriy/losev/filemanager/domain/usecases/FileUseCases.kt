package dmitriy.losev.filemanager.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.core.Result
import dmitriy.losev.core.domain.usecases.MessageDigestUseCases
import dmitriy.losev.database.domain.models.RoomFileModel
import dmitriy.losev.database.domain.usecases.RoomFileUseCases
import dmitriy.losev.filemanager.core.file.FileExtension
import dmitriy.losev.filemanager.core.file.SortedOrder
import dmitriy.losev.filemanager.core.file.SortedType
import dmitriy.losev.filemanager.data.mappers.FileMapper
import dmitriy.losev.filemanager.domain.models.FileModel
import dmitriy.losev.filemanager.domain.repositories.FileRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File

class FileUseCases(
    private val fileMapper: FileMapper,
    private val fileRepository: FileRepository,
    private val roomFileUseCases: RoomFileUseCases,
    private val messageDigestUseCases: MessageDigestUseCases
) : BaseUseCase() {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getFiles(path: String): Result<List<FileModel>> = safeCall {
        fileRepository.getFiles(path = path).map { file ->
            fileMapper.map(value = file)
        }
    }

    suspend fun sortingFiles(
        files: List<FileModel>,
        sortedOrder: SortedOrder,
        sortedType: SortedType
    ): Result<List<FileModel>> = safeCall {
        files.sortedWith(comparator = sortedType.getComparator(comparator = sortedOrder.comparator))
    }

    suspend fun checkChangingFile(files: List<FileModel>): Result<List<FileModel>> = safeCall {
        files.map { file ->
            file.apply {
                if (extension != FileExtension.FOLDER) {
                    isChanged = checkChangingFile(fileModel = this)
                }
            }
        }
    }

    fun createHashFiles(path: String) = runBlocking {
        addFilesToDataBase(path = path)
    }

    private suspend fun addFilesToDataBase(path: String) {
        File(path).listFiles()?.forEach { file ->
            if (file.isDirectory) {
                addFilesToDataBase(path = file.absolutePath)
            } else {
                addFileToDataBase(path = file.absolutePath)
            }
        }
    }

    private suspend fun addFileToDataBase(path: String) {
        val hash = messageDigestUseCases.getFileHash(path = path)
        val roomFileModel = RoomFileModel(path = path, hash = hash)
        withContext(Dispatchers.IO) {
            roomFileUseCases.addFile(file = roomFileModel)
        }
    }

    private suspend fun checkChangingFile(fileModel: FileModel): Boolean {
        val hash = messageDigestUseCases.getFileHash(path = fileModel.path)
        val roomFileModel = roomFileUseCases.getFile(path = fileModel.path)
        return !hash.contentEquals(other = roomFileModel?.hash)
    }
}