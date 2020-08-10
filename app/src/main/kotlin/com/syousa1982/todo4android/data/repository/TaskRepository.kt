package com.syousa1982.todo4android.data.repository

import androidx.annotation.WorkerThread
import com.syousa1982.todo4android.data.db.dao.TaskDao
import com.syousa1982.todo4android.data.db.entity.Task


class TaskRepository(private val dao: TaskDao) {

    val tasks = dao.getAll()

    @WorkerThread
    suspend fun insert(task: Task) {
        dao.insert(task)
    }
}