package dmitriy.losev.core.data.di

import dmitriy.losev.core.data.repositories.MessageDigestRepositoryImpl
import dmitriy.losev.core.domain.repositories.MessageDigestRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val coreRepositoryModule = module {

    factoryOf(::MessageDigestRepositoryImpl) {
        bind<MessageDigestRepository>()
    }
}