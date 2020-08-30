package com.syousa1982.todo4android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.syousa1982.todo4android.data.db.dao.TaskDao
import com.syousa1982.todo4android.data.db.entity.Task
import com.syousa1982.todo4android.data.db.entity.TaskStatusConverter

@Database(entities = arrayOf(Task::class), version = 1, exportSchema = false)
@TypeConverters(TaskStatusConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}