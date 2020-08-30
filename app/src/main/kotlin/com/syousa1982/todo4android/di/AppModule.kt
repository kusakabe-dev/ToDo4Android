package com.syousa1982.todo4android.di

import com.syousa1982.todo4android.data.repository.TaskRepository
import org.koin.dsl.module

object AppModule {
    val module = module {
        single { TaskRepository(get()) }
    }
}