package com.syousa1982.todo4android

import android.app.Application
import com.syousa1982.todo4android.di.AppModule
import org.koin.android.ext.android.startKoin

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(AppModule.instance))
    }

    protected open fun isInUnitTests() = false
}