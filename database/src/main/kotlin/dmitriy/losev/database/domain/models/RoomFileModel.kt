package dmitriy.losev.database.domain.models

data class RoomFileModel(val path: String, val hash: ByteArray) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RoomFileModel

        if (path != other.path) return false
        return hash.contentEquals(other.hash)
    }

    override fun hashCode(): Int {
        var result = path.hashCode()
        result = 31 * result + hash.contentHashCode()
        return result
    }
}