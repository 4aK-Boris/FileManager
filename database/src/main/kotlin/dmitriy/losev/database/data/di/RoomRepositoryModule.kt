package dmitriy.losev.database.data.di

import dmitriy.losev.database.data.repositories.RoomFileRepositoryImpl
import dmitriy.losev.database.domain.repositories.RoomFileRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val roomRepositoryModule = module {

    factoryOf(::RoomFileRepositoryImpl) {
        bind<RoomFileRepository>()
    }
}