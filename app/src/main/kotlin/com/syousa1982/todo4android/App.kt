package com.syousa1982.todo4android

import android.app.Application

open class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }

    protected open fun isInUnitTests() = false
}