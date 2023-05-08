package dmitriy.losev.database.domain.repositories

import dmitriy.losev.database.data.entity.FileEntity

interface RoomFileRepository {

    suspend fun addFile(file: FileEntity)

    suspend fun addFiles(files: List<FileEntity>)

    suspend fun getFile(path: String): FileEntity?

    suspend fun hasFile(file: FileEntity): Boolean

    suspend fun hasFile(path: String): Boolean

    suspend fun deleteFile(path: String)

    suspend fun deleteFile(file: FileEntity)

    suspend fun deleteAll()

    suspend fun deleteAll(files: List<FileEntity>)
}