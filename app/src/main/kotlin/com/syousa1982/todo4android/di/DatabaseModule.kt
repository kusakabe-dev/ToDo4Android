package com.syousa1982.todo4android.di

import android.content.Context
import androidx.room.Room
import com.syousa1982.todo4android.data.db.AppDatabase
import com.syousa1982.todo4android.utils.Constants.DB_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object DatabaseModule {

    val module = module {
        single { provideDatabese(androidContext()) }
        single { provideTaskDao(get()) }
    }

    fun provideDatabese(context: Context)
        = Room
        .databaseBuilder(context, AppDatabase::class.java, DB_NAME)
        .build()

    fun provideTaskDao(db: AppDatabase) = db.taskDao()
}

//@Module
//class DatabaseModule {
//    @Singleton
//    @Provides
//    fun provideDatabaseName() = DB_NAME
//
//    @Singleton
//    @Provides
//    fun provideDatabaseVersion() = DB_VERSION
//
//    @Singleton
//    @Provides
//    fun providePrefName() = SHARED_PREF_NAME
//
//    @Singleton
//    @Provides
//    fun provideAppDatabase(context: Context) =
//        Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME).build()
//
//    @Singleton
//    @Provides
//    fun provideTaskDao(appDatabase: AppDatabase) = appDatabase.taskDao()
//}