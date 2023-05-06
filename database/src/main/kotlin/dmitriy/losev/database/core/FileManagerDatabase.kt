package dmitriy.losev.database.core

import androidx.room.Database
import androidx.room.RoomDatabase
import dmitriy.losev.database.data.dao.FileDao
import dmitriy.losev.database.data.entity.FileEntity

@Database(entities = [FileEntity::class], version = 1)
abstract class FileManagerDatabase : RoomDatabase() {
    abstract fun fileDao(): FileDao
}