package dmitriy.losev.filemanager.domain.usecases

import android.os.Build
import androidx.annotation.RequiresApi
import dmitriy.losev.core.core.BaseUseCase
import dmitriy.losev.core.core.Result
import dmitriy.losev.filemanager.core.file.SortedOrder
import dmitriy.losev.filemanager.core.file.SortedType
import dmitriy.losev.filemanager.data.mappers.FileMapper
import dmitriy.losev.filemanager.domain.models.FileModel
import dmitriy.losev.filemanager.domain.repositories.FileRepository

class FileUseCases(
    private val fileMapper: FileMapper,
    private val fileRepository: FileRepository
) : BaseUseCase() {

    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getFiles(path: String): Result<List<FileModel>> = safeCall {
        fileRepository.getFiles(path = path).map { file ->
            fileMapper.map(value = file)
        }
    }

    fun sortedFiles(
        files: List<FileModel>,
        sortedOrder: SortedOrder,
        sortedType: SortedType
    ): List<FileModel> {
        val comparator = sortedType.getComparator(comparator = sortedOrder.comparator)
        return files.sortedWith(comparator = comparator)
    }
}