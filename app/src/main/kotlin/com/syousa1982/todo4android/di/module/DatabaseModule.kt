package com.syousa1982.todo4android.di.module

import android.content.Context
import androidx.room.Room
import com.syousa1982.todo4android.data.db.AppDatabase
import com.syousa1982.todo4android.utils.Constants.DB_NAME
import com.syousa1982.todo4android.utils.Constants.DB_VERSION
import com.syousa1982.todo4android.utils.Constants.SHARED_PREF_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabaseName() = DB_NAME

    @Singleton
    @Provides
    fun provideDatabaseVersion() = DB_VERSION

    @Singleton
    @Provides
    fun providePrefName() = SHARED_PREF_NAME

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context) =
        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()

    @Singleton
    @Provides
    fun provideTaskDao(appDatabase: AppDatabase) = appDatabase.taskDao()

}