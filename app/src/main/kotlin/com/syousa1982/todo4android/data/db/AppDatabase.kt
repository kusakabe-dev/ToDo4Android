package com.syousa1982.todo4android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syousa1982.todo4android.data.db.dao.TaskDao
import com.syousa1982.todo4android.data.db.entity.Task

@Database(entities = arrayOf(Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}