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
            var read = stream.read(buffer, 0, DEFAULT_BUFFER_SIZE)
            while (read > -1) {
                messageDigest.update(buffer, 0, read)
                read = stream.read(buffer, 0, DEFAULT_BUFFER_SIZE)
            }
        }
        return messageDigest.digest()
    }
}