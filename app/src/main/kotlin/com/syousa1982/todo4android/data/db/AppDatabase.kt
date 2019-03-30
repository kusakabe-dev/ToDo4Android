package com.syousa1982.todo4android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syousa1982.todo4android.data.db.dao.TaskListDao
import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListEntity

@Database(entities = arrayOf(TaskListEntity::class, TaskEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskListDao(): TaskListDao
}