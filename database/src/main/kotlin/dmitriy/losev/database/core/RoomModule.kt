package dmitriy.losev.database.core

import androidx.room.Room
import dmitriy.losev.database.data.di.daoModule
import dmitriy.losev.database.data.di.roomMapperModule
import dmitriy.losev.database.data.di.roomRepositoryModule
import dmitriy.losev.database.domain.di.roomUseCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {

    includes(roomMapperModule, roomRepositoryModule, roomUseCaseModule, daoModule)

    single<FileManagerDatabase> {
        Room.databaseBuilder(
            context = androidContext(),
            klass = FileManagerDatabase::class.java,
            name = DATABASE_NAME
        ).build()
    }
}
