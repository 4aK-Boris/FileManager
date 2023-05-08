package dmitriy.losev.database.usecases

import dmitriy.losev.database.domain.models.RoomFileModel
import dmitriy.losev.database.domain.usecases.RoomFileUseCases
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestRoomFileUseCases : KoinTest {

    private val useCases by inject<RoomFileUseCases>()

    @Before
    fun before() = runBlocking {
        useCases.deleteAll(files = listOf(testRoomFileModel1, testRoomFileModel2, testRoomFileModel3))
    }

    @After
    fun after() = runBlocking {
        useCases.deleteAll(files = listOf(testRoomFileModel1, testRoomFileModel2, testRoomFileModel3))
    }

    @Test
    fun testAddFile(): Unit = runBlocking {
        useCases.addFile(file = testRoomFileModel1)
        useCases.addFile(file = testRoomFileModel2)

        val result11 = useCases.hasFile(file = testRoomFileModel1)
        val result21 = useCases.hasFile(file = testRoomFileModel2)
        val result31 = useCases.hasFile(file = testRoomFileModel3)

        val result12 = useCases.hasFile(path = testRoomFileModel1.path)
        val result22 = useCases.hasFile(path = testRoomFileModel2.path)
        val result32 = useCases.hasFile(path = testRoomFileModel3.path)

        assertTrue(actual = result11)
        assertTrue(actual = result21)
        assertFalse(actual = result31)

        assertTrue(actual = result12)
        assertTrue(actual = result22)
        assertFalse(actual = result32)
    }

    @Test
    fun testAddFiles() = runBlocking {
        useCases.addFiles(files = listOf(testRoomFileModel2, testRoomFileModel3))

        val result11 = useCases.hasFile(file = testRoomFileModel1)
        val result21 = useCases.hasFile(file = testRoomFileModel2)
        val result31 = useCases.hasFile(file = testRoomFileModel3)

        val result12 = useCases.hasFile(path = testRoomFileModel1.path)
        val result22 = useCases.hasFile(path = testRoomFileModel2.path)
        val result32 = useCases.hasFile(path = testRoomFileModel3.path)

        assertFalse(actual = result11)
        assertTrue(actual = result21)
        assertTrue(actual = result31)

        assertFalse(actual = result12)
        assertTrue(actual = result22)
        assertTrue(actual = result32)
    }

    @Test
    fun testGetFile() = runBlocking {
        useCases.addFiles(files = listOf(testRoomFileModel1, testRoomFileModel3))

        val result1 = useCases.getFile(path = testRoomFileModel1.path)
        val result2 = useCases.getFile(path = testRoomFileModel2.path)
        val result3 = useCases.getFile(path = testRoomFileModel3.path)

        assertEquals(expected = result1, actual = testRoomFileModel1)
        assertEquals(expected = result2, actual = null)
        assertEquals(expected = result3, actual = testRoomFileModel3)
    }

    @Test
    fun testDeleteFile() = runBlocking {
        useCases.addFiles(files = listOf(testRoomFileModel1, testRoomFileModel2, testRoomFileModel3))

        useCases.deleteFile(file = testRoomFileModel1)
        useCases.deleteFile(path = testRoomFileModel3.path)

        val result1 = useCases.hasFile(file = testRoomFileModel1)
        val result2 = useCases.hasFile(file = testRoomFileModel2)
        val result3 = useCases.hasFile(file = testRoomFileModel3)

        assertFalse(actual = result1)
        assertTrue(actual = result2)
        assertFalse(actual = result3)
    }


    companion object {

        private val testRoomFileModel1 = RoomFileModel(
            path = "/data/data/dmitriy.losev.database.test/files/test.txt",
            hash = byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        )

        private val testRoomFileModel2 = RoomFileModel(
            path = "jh wadb iucygweduiyvqwgduywa",
            hash = byteArrayOf(0, 1, 2, 3, 4, 5)
        )

        private val testRoomFileModel3 = RoomFileModel(
            path = "9843unm957943y8b34y967b543y 68754",
            hash = byteArrayOf(0, 1, 2, 3)
        )
    }
}