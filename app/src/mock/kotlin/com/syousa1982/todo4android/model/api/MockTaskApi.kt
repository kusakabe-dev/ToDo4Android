package com.syousa1982.todo4android.model.api

import com.syousa1982.todo4android.model.entity.Task
import io.reactivex.Single
import retrofit2.mock.BehaviorDelegate

/**
 * タスク APIモック
 */
class MockTaskApi(private val delegate: BehaviorDelegate<TaskApi>) : TaskApi {

    override fun fetchTasks(): Single<List<Task>> {
        val tasks = listOf(
                Task("1", "朝食を作る", true),
                Task("2", "買い物", false),
                Task("3", "通勤", false)
        )
        return delegate.returningResponse(tasks).fetchTasks()
    }

    override fun addTasks(task: Task): Single<MessageResponse> {
        val response = MessageResponse(201)
        return delegate.returningResponse(response).addTasks(task)
    }

    override fun updateTasks(task: Task): Single<MessageResponse> {
        val response = MessageResponse(204)
        return delegate.returningResponse(response).updateTasks(task)
    }
}