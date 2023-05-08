package dmitriy.losev.filemanager.data.di

import dmitriy.losev.filemanager.data.mappers.FileMapper
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val mapperModule = module {

   factoryOf(::FileMapper)
}