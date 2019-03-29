package com.syousa1982.todo4android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syousa1982.todo4android.data.db.dao.TaskListDao
import com.syousa1982.todo4android.data.db.entity.Task
import com.syousa1982.todo4android.data.db.entity.TaskList

@Database(entities = arrayOf(TaskList::class, Task::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskListDao(): TaskListDao
}