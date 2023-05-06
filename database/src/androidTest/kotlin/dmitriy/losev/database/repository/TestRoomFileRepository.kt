package dmitriy.losev.database.repository

import dmitriy.losev.database.core.roomModule
import dmitriy.losev.database.data.entity.FileEntity
import dmitriy.losev.database.domain.repositories.RoomFileRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject

class TestRoomFileRepository : KoinTest {

    private val repository by inject<RoomFileRepository>()

    @Test
    fun testAddFile() = runBlocking {
        repository.addFile(file = testFileEntity)
    }

    companion object {

        private val testFileEntity = FileEntity(
            path = "/data/data/dmitriy.losev.database.test/files/test.txt",
            hash = byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        )
    }
}