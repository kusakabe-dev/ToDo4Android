package com.syousa1982.todo4android.model.repository

import com.syousa1982.todo4android.model.api.MessageResponse
import com.syousa1982.todo4android.model.api.TaskApi
import com.syousa1982.todo4android.model.entity.Task
import io.reactivex.Single

class MockTaskRepository(private val api: TaskApi) : ITaskRepository {

    override fun fetchTasks(): Single<List<Task>> {
        return api.fetchTasks()
    }

    override fun addTasks(task: Task): Single<MessageResponse> {
        return api.addTasks(task)
    }

    override fun updateTasks(task: Task): Single<MessageResponse> {
        return api.updateTasks(task)
    }
}