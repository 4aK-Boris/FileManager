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
}