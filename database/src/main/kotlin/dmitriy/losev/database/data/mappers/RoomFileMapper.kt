package dmitriy.losev.database.data.mappers

import dmitriy.losev.database.data.entity.FileEntity
import dmitriy.losev.database.domain.models.RoomFileModel

class RoomFileMapper {

    fun map(value: RoomFileModel): FileEntity {
        return FileEntity(
            path = value.path,
            hash = value.hash
        )
    }

    fun map(value: FileEntity): RoomFileModel {
        return RoomFileModel(
            path = value.path,
            hash = value.hash
        )
    }
}