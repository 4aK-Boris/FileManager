package dmitriy.losev.database.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dmitriy.losev.database.data.entity.FileEntity

@Dao
interface FileDao {

    @Query("SELECT * FROM files")
    fun getAll(): List<FileEntity>

    @Query("SELECT * FROM files WHERE path = :path")
    fun getFile(path: String): FileEntity?

    @Query("SELECT * FROM files WHERE path = :path AND hash = :hash")
    fun getFile(path: String, hash: ByteArray): FileEntity?

    @Query("SELECT path FROM files WHERE hash NOT IN (:hashes)")
    fun getModifiedFiles(hashes: List<ByteArray>): List<String>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFile(file: FileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addAllFiles(files: List<FileEntity>)

    @Delete
    fun delete(file: FileEntity)

    @Query("DELETE FROM files WHERE path = :path")
    fun delete(path: String)

    @Delete
    fun deleteAll(files: List<FileEntity>)

    @Query("DELETE FROM files")
    fun deleteAll()
}