package dmitriy.losev.database.data.di

import dmitriy.losev.database.data.mappers.RoomFileMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val roomMapperModule = module {

    factoryOf(::RoomFileMapper)
}