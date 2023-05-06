package dmitriy.losev.database.di

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import dmitriy.losev.database.core.roomModule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class TestRoomDI: KoinTest {

    @Test
    fun verifyKoin() {

        koinApplication {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(roomModule)
        }.checkModules()
    }
}