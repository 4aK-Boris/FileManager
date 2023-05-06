package dmitriy.losev.database.domain.repositories

import dmitriy.losev.database.data.entity.FileEntity
import dmitriy.losev.database.domain.models.RoomFileModel

interface RoomFileRepository {

    suspend fun addFile(file: FileEntity)

    suspend fun addFiles(files: List<FileEntity>)
}