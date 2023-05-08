package dmitriy.losev.database.domain.usecases

import dmitriy.losev.database.data.mappers.RoomFileMapper
import dmitriy.losev.database.domain.models.RoomFileModel
import dmitriy.losev.database.domain.repositories.RoomFileRepository

class RoomFileUseCases(
    private val roomFileMapper: RoomFileMapper,
    private val roomFileRepository: RoomFileRepository
) {

    suspend fun addFile(file: RoomFileModel) {
        roomFileRepository.addFile(file = roomFileMapper.map(value = file))
    }

    suspend fun addFiles(files: List<RoomFileModel>) {
        roomFileRepository.addFiles(files = files.map { file -> roomFileMapper.map(value = file) })
    }

    suspend fun getFile(path: String): RoomFileModel? {
        return roomFileRepository.getFile(path = path)?.let { roomFileMapper.map(value = it) }
    }

    suspend fun hasFile(file: RoomFileModel): Boolean {
        return roomFileRepository.hasFile(file = roomFileMapper.map(value = file))
    }

    suspend fun hasFile(path: String): Boolean {
        return roomFileRepository.hasFile(path = path)
    }

    suspend fun deleteFile(path: String) {
        roomFileRepository.deleteFile(path = path)
    }

    suspend fun deleteFile(file: RoomFileModel) {
        roomFileRepository.deleteFile(file = roomFileMapper.map(value = file))
    }

    suspend fun deleteAll() {
        roomFileRepository.deleteAll()
    }

    suspend fun deleteAll(files: List<RoomFileModel>) {
        roomFileRepository.deleteAll(files = files.map { file ->
            roomFileMapper.map(value = file)
        })
    }
}