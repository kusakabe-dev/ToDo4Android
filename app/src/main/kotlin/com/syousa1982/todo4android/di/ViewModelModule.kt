package com.syousa1982.todo4android.di

import com.syousa1982.todo4android.presentation.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val module = module {
        viewModel { TaskViewModel(get()) }
    }
}