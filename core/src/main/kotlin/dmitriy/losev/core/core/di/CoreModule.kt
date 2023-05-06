package dmitriy.losev.core.core.di

import dmitriy.losev.core.core.SHA256
import dmitriy.losev.core.core.exception.ErrorHandler
import dmitriy.losev.core.data.di.coreRepositoryModule
import dmitriy.losev.core.domain.di.coreUseCaseModule
import org.koin.dsl.module
import java.security.MessageDigest

val coreModule = module {

    single {
        ErrorHandler()
    }

    factory {
        MessageDigest.getInstance(SHA256)
    }

    includes(coreRepositoryModule, coreUseCaseModule)
}