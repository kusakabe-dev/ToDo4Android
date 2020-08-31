package com.syousa1982.todo4android

import android.app.Application
import com.syousa1982.todo4android.di.AppModule
import com.syousa1982.todo4android.di.DatabaseModule
import com.syousa1982.todo4android.di.ViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

open class TodoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
//            androidLogger()
            androidContext(this@TodoApp)
            modules(listOf(DatabaseModule.module, AppModule.module, ViewModelModule.module))
        }
    }

}