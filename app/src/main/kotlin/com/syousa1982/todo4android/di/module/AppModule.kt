package com.syousa1982.todo4android.di.module

import android.app.Application
import android.content.Context
import com.syousa1982.todo4android.data.db.dao.TaskDao
import com.syousa1982.todo4android.data.repository.TaskRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    fun provideContext(application: Application): Context = application

    @Singleton
    @Provides
    fun provideTaskRepository(taskDao: TaskDao) = TaskRepository(taskDao)
}