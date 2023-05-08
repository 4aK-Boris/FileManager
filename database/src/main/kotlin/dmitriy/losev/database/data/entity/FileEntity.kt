package dmitriy.losev.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "files")
data class FileEntity(
    @PrimaryKey
    @ColumnInfo(name = "path")
    val path: String,

    @ColumnInfo(name = "hash")
    val hash: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as FileEntity

        if (path != other.path) return false
        return hash.contentEquals(other.hash)
    }

    override fun hashCode(): Int {
        var result = path.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}