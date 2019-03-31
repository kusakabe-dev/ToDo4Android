package com.syousa1982.todo4android.di

import com.syousa1982.todo4android.data.repository.ITaskListRepository
import com.syousa1982.todo4android.data.repository.TaskListRepository
import com.syousa1982.todo4android.presentation.tasklist.TaskListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object AppModule {
    val instance = module {
        // region Presentation Layer
        viewModel { TaskListViewModel() }
        // endregion

        // region Data Layer Database
        single { DatabaseModule.getDatabase(androidContext()) }
        single { DatabaseModule.getTaskListDao(get()) }
        // endregion

        // region Data Layer Repository
        single<ITaskListRepository> { TaskListRepository(get()) }
        // endregion
    }
}