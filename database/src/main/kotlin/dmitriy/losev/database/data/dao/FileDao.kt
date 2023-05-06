package dmitriy.losev.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import dmitriy.losev.database.data.entity.FileEntity

@Dao
interface FileDao {

    @Query("SELECT * FROM files")
    fun getAll(): List<FileEntity>

    @Query("SELECT * FROM files WHERE path = :path")
    fun getFile(path: String): FileEntity

    @Query("SELECT path FROM files WHERE hash NOT IN (:hashes)")
    fun getModifiedFiles(hashes: List<ByteArray>): List<String>

    @Insert
    fun addFile(file: FileEntity)

    @Insert
    fun addAllFiles(files: List<FileEntity>)

    @Delete
    fun delete(file: FileEntity)

    @Delete
    fun deleteAll(files: List<FileEntity>)
}