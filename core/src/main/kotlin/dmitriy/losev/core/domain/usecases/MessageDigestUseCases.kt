package dmitriy.losev.core.domain.usecases

import dmitriy.losev.core.domain.repositories.MessageDigestRepository

class MessageDigestUseCases(
    private val messageDigestRepository: MessageDigestRepository
) {

    suspend fun getFileHash(path: String): ByteArray {
        return messageDigestRepository.getFileHash(path = path)
    }
}