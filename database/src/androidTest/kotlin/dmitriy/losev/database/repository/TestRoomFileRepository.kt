package dmitriy.losev.database.repository

import dmitriy.losev.database.data.entity.FileEntity
import dmitriy.losev.database.domain.repositories.RoomFileRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestRoomFileRepository : KoinTest {

    private val repository by inject<RoomFileRepository>()

    @Before
    fun before() = runBlocking {
        repository.deleteAll(files = listOf(testFileEntity1, testFileEntity2, testFileEntity3))
    }

    @After
    fun after() = runBlocking {
        repository.deleteAll(files = listOf(testFileEntity1, testFileEntity2, testFileEntity3))
    }

    @Test
    fun testAddFile(): Unit = runBlocking {
        repository.addFile(file = testFileEntity1)
        repository.addFile(file = testFileEntity2)

        val result11 = repository.hasFile(file = testFileEntity1)
        val result21 = repository.hasFile(file = testFileEntity2)
        val result31 = repository.hasFile(file = testFileEntity3)

        val result12 = repository.hasFile(path = testFileEntity1.path)
        val result22 = repository.hasFile(path = testFileEntity2.path)
        val result32 = repository.hasFile(path = testFileEntity3.path)

        assertTrue(actual = result11)
        assertTrue(actual = result21)
        assertFalse(actual = result31)

        assertTrue(actual = result12)
        assertTrue(actual = result22)
        assertFalse(actual = result32)
    }

    @Test
    fun testAddFiles() = runBlocking {
        repository.addFiles(files = listOf(testFileEntity2, testFileEntity3))

        val result11 = repository.hasFile(file = testFileEntity1)
        val result21 = repository.hasFile(file = testFileEntity2)
        val result31 = repository.hasFile(file = testFileEntity3)

        val result12 = repository.hasFile(path = testFileEntity1.path)
        val result22 = repository.hasFile(path = testFileEntity2.path)
        val result32 = repository.hasFile(path = testFileEntity3.path)

        assertFalse(actual = result11)
        assertTrue(actual = result21)
        assertTrue(actual = result31)

        assertFalse(actual = result12)
        assertTrue(actual = result22)
        assertTrue(actual = result32)
    }

    @Test
    fun testGetFile() = runBlocking {
        repository.addFiles(files = listOf(testFileEntity1, testFileEntity3))

        val result1 = repository.getFile(path = testFileEntity1.path)
        val result2 = repository.getFile(path = testFileEntity2.path)
        val result3 = repository.getFile(path = testFileEntity3.path)

        assertEquals(expected = result1, actual = testFileEntity1)
        assertEquals(expected = result2, actual = null)
        assertEquals(expected = result3, actual = testFileEntity3)
    }

    @Test
    fun testDeleteFile() = runBlocking {
        repository.addFiles(files = listOf(testFileEntity1, testFileEntity2, testFileEntity3))

        repository.deleteFile(file = testFileEntity1)
        repository.deleteFile(path = testFileEntity3.path)

        val result1 = repository.hasFile(file = testFileEntity1)
        val result2 = repository.hasFile(file = testFileEntity2)
        val result3 = repository.hasFile(file = testFileEntity3)

        assertFalse(actual = result1)
        assertTrue(actual = result2)
        assertFalse(actual = result3)
    }


    companion object {

        private val testFileEntity1 = FileEntity(
            path = "/data/data/dmitriy.losev.database.test/files/test.txt",
            hash = byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        )

        private val testFileEntity2 = FileEntity(
            path = "jh wadb iucygweduiyvqwgduywa",
            hash = byteArrayOf(0, 1, 2, 3, 4, 5)
        )

        private val testFileEntity3 = FileEntity(
            path = "9843unm957943y8b34y967b543y 68754",
            hash = byteArrayOf(0, 1, 2, 3)
        )
    }
}