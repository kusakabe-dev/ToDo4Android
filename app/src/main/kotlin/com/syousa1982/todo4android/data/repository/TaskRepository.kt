package com.syousa1982.todo4android.data.repository

import androidx.annotation.WorkerThread
import com.syousa1982.todo4android.data.db.dao.TaskDao
import com.syousa1982.todo4android.data.db.entity.Task
import javax.inject.Inject


class TaskRepository @Inject constructor(private val dao: TaskDao) {

    val tasks = dao.getAll()

    @WorkerThread
    suspend fun insert(task: Task) {
        dao.insert(task)
    }

    @WorkerThread
    suspend fun add(name: String) {
        dao.insert(Task(name = name))
    }
}