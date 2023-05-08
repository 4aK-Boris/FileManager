package dmitriy.losev.core.repositories

import dmitriy.losev.core.core.di.coreModule
import dmitriy.losev.core.domain.repositories.MessageDigestRepository
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class TestMessageDigestRepository : KoinTest {

    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(coreModule)
    }

    private val repository by inject<MessageDigestRepository>()

    @Test
    fun testMessageDigest() = runBlocking {
        val hash1 = repository.getFileHash(path = fileName)
        val hash2 = repository.getFileHash(path = fileName)

        assertEquals(expected = hash1.size, actual = 32)
        assertEquals(expected = hash2.size, actual = 32)

        assertContentEquals(expected = hash1, actual = hash2)
    }

    companion object {
        private const val fileName =
            "C:\\Users\\nagib\\AndroidStudioProjects\\FileManager\\CroIndex.dat"
    }
}