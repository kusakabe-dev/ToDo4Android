package com.syousa1982.todo4android.di.componet

import android.app.Application
import com.syousa1982.todo4android.di.module.AppModule
import com.syousa1982.todo4android.di.module.DatabaseModule
import com.syousa1982.todo4android.presentation.TaskFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, DatabaseModule::class]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }

    fun inject(taskFragment: TaskFragment)
}