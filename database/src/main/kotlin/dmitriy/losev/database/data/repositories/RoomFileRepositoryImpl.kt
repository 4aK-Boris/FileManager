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

    override suspend fun getFile(path: String): FileEntity? {
        return fileDao.getFile(path = path)
    }

    override suspend fun hasFile(file: FileEntity): Boolean {
        return fileDao.getFile(path = file.path, hash = file.hash) != null
    }

    override suspend fun hasFile(path: String): Boolean {
        return fileDao.getFile(path = path) != null
    }

    override suspend fun deleteFile(path: String) {
        fileDao.delete(path = path)
    }

    override suspend fun deleteFile(file: FileEntity) {
        fileDao.delete(file = file)
    }

    override suspend fun deleteAll() {
        fileDao.deleteAll()
    }

    override suspend fun deleteAll(files: List<FileEntity>) {
        fileDao.deleteAll(files = files)
    }
}