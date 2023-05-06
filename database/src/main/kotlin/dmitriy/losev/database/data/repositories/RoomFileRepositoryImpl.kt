package dmitriy.losev.database.data.repositories

import dmitriy.losev.database.data.dao.FileDao
import dmitriy.losev.database.data.entity.FileEntity
import dmitriy.losev.database.domain.repositories.RoomFileRepository

class RoomFileRepositoryImpl(
    private val fileDao: FileDao
) : RoomFileRepository {
    override suspend fun addFile(file: FileEntity) {
        fileDao.addFile(file)
    }

    override suspend fun addFiles(files: List<FileEntity>) {
        fileDao.addAllFiles(files = files)
    }

}