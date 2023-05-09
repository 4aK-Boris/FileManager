package dmitriy.losev.filemanager.domain.di

import dmitriy.losev.filemanager.domain.usecases.FileUseCases
import dmitriy.losev.filemanager.domain.usecases.IntentUseCases
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val useCaseModule = module {

    factoryOf(::FileUseCases)

    factoryOf(::IntentUseCases)
}