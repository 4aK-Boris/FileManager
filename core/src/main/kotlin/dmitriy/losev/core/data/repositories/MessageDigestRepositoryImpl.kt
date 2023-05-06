package dmitriy.losev.core.data.repositories

import dmitriy.losev.core.domain.repositories.MessageDigestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.FileInputStream
import java.security.MessageDigest

class MessageDigestRepositoryImpl(
    private val messageDigest: MessageDigest
): MessageDigestRepository {

    override suspend fun getFileHash(path: String): ByteArray {
        withContext(Dispatchers.IO) {
            val stream = FileInputStream(path).buffered()
            val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
            var length = stream.read(buffer)
            while (length > 0) {
                length = stream.read(buffer)
                messageDigest.update(buffer, 0, length)
            }
        }
        return messageDigest.digest()
    }
}