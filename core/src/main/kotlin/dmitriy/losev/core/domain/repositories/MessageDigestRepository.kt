package dmitriy.losev.core.domain.repositories

interface MessageDigestRepository {

    suspend fun getFileHash(path: String): ByteArray
}