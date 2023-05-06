package dmitriy.losev.database.data.di

import dmitriy.losev.database.core.FileManagerDatabase
import org.koin.dsl.module

val daoModule = module {

    factory {
        get<FileManagerDatabase>().fileDao()
    }
}