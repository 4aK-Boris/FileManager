package dmitriy.losev.filemanager.presentation.di

import dmitriy.losev.filemanager.core.MainViewModel
import dmitriy.losev.filemanager.presentation.viewmodels.FileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::MainViewModel)

    viewModelOf(::FileViewModel)
}