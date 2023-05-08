package dmitriy.losev.filemanager.data.repositories

import dmitriy.losev.filemanager.data.dto.FileDTO
import dmitriy.losev.filemanager.domain.repositories.FileRepository
import java.io.File

class FileRepositoryImpl: FileRepository {

    override suspend fun getFiles(path: String): List<FileDTO> {
        return File(path).listFiles()?.map { file -> FileDTO(file = file) } ?: emptyList()
    }
}