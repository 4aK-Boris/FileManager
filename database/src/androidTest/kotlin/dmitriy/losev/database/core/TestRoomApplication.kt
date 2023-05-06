package dmitriy.losev.database.core

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin


class TestRoomApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TestRoomApplication)
            modules(roomModule)
        }
    }
}