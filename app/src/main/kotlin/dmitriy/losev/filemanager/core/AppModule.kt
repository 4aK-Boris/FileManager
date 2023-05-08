package dmitriy.losev.filemanager.core

import dmitriy.losev.core.core.di.coreModule
import dmitriy.losev.database.core.roomModule
import dmitriy.losev.filemanager.data.di.mapperModule
import dmitriy.losev.filemanager.data.di.repositoryModule
import dmitriy.losev.filemanager.domain.di.useCaseModule
import dmitriy.losev.filemanager.presentation.di.viewModelModule
import org.koin.dsl.module


val appModule = module {

    includes(
        coreModule,
        viewModelModule,
        repositoryModule,
        mapperModule,
        useCaseModule,
        roomModule
    )
}
