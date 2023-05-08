package dmitriy.losev.filemanager.domain.repositories

import dmitriy.losev.filemanager.data.dto.FileDTO

interface FileRepository {

    suspend fun getFiles(path: String): List<FileDTO>
}