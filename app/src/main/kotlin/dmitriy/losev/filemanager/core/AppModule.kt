package dmitriy.losev.filemanager.core

import dmitriy.losev.core.core.di.coreModule
import org.koin.dsl.module


val appModule = module {

    includes(coreModule)
}
