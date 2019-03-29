package com.syousa1982.todo4android.di

import android.content.Context
import androidx.room.Room
import com.syousa1982.todo4android.BuildConfig
import com.syousa1982.todo4android.data.db.AppDatabase

object DatabaseModule {
    private const val ROOM_DATABASE_NAME = "Room_Database_${BuildConfig.FLAVOR}"

    fun getDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, ROOM_DATABASE_NAME)
                .build()
    }
}