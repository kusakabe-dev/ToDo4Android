package com.syousa1982.todo4android.di

import com.syousa1982.todo4android.data.repository.ITaskListRepository
import com.syousa1982.todo4android.data.repository.TaskListRepository
import com.syousa1982.todo4android.domain.usecase.IToDoUseCase
import com.syousa1982.todo4android.domain.usecase.ToDoUseCase
import com.syousa1982.todo4android.presentation.tasklist.TaskListViewModel
import com.syousa1982.todo4android.util.rx.AppSchedulerProvider
import com.syousa1982.todo4android.util.rx.SchedulerProvider
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

object AppModule {
    val instance = module {
        // region Presentation Layer
        viewModel { TaskListViewModel(get()) }
        // endregion

        // region Domain Layer
        factory<IToDoUseCase> { ToDoUseCase(get(), get()) }
        // endregion

        // region Data Layer Database
        single { DatabaseModule.getDatabase(androidContext()) }
        single { DatabaseModule.getTaskListDao(get()) }
        // endregion

        // region Data Layer Repository
        single<ITaskListRepository> { TaskListRepository(get()) }
        // endregion

        // region Rx
        single<SchedulerProvider> { AppSchedulerProvider() }
        // endregion
    }
}