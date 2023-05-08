package dmitriy.losev.filemanager.data.di

import dmitriy.losev.filemanager.data.repositories.FileRepositoryImpl
import dmitriy.losev.filemanager.domain.repositories.FileRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val repositoryModule = module {

    factoryOf(::FileRepositoryImpl) {
        bind<FileRepository>()
    }
}