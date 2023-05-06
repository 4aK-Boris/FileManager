package dmitriy.losev.core.core.di

import dmitriy.losev.core.core.exception.ErrorHandler
import org.koin.dsl.module

val coreModule = module {

    single {
        ErrorHandler()
    }
}