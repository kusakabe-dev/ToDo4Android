package com.syousa1982.todo4android

import android.app.Application
import com.syousa1982.todo4android.di.componet.AppComponent
import com.syousa1982.todo4android.di.componet.DaggerAppComponent

class TodoApp : Application() {
    lateinit var appComponent: AppComponent
//
//    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
//        = DaggerAppComponent.factory().run { create(this@TodoApp) }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

}