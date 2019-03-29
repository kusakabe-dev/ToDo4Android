package com.syousa1982.todo4android.di

import com.syousa1982.todo4android.presentation.tasklist.TaskListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object AppModule {
    val instance = module {
        // region Presentation Layer
        viewModel { TaskListViewModel() }
        // endregion

        // region Data Layer Database
        single { DatabaseModule.getDatabase(androidContext()) }
        // endregion
    }
}