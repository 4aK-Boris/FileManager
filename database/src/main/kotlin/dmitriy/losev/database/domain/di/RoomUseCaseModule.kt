package dmitriy.losev.database.domain.di

import dmitriy.losev.database.domain.usecases.RoomFileUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val roomUseCaseModule = module {

    factoryOf(::RoomFileUseCases)
}