package com.syousa1982.todo4android.model.api

import com.syousa1982.todo4android.model.api.request.Request
import com.syousa1982.todo4android.model.api.response.ItemResponse
import com.syousa1982.todo4android.model.api.response.ItemsResponse
import com.syousa1982.todo4android.model.api.response.MessageResponse
import com.syousa1982.todo4android.model.entity.Task
import io.reactivex.Single
import retrofit2.mock.BehaviorDelegate

/**
 * タスク APIモック
 */
class MockTaskApi(private val delegate: BehaviorDelegate<TaskApi>) : TaskApi {

    override fun fetchTasks(): Single<ItemsResponse<List<Task>>> {
        val tasks = listOf(
                Task("1", "朝食を作る", true),
                Task("2", "買い物", false),
                Task("3", "通勤", false)
        )
        return delegate.returningResponse(ItemsResponse(tasks)).fetchTasks()
    }

    override fun fetchTask(id: String): Single<ItemResponse<Task>> {
        val task = Task("3", "通勤", false)
        return delegate.returningResponse(ItemsResponse(task)).fetchTask(id)
    }

    override fun addTasks(taskRequest: Request<Task>): Single<MessageResponse> {
        val response = MessageResponse(true, "タスクを登録しました")
        return delegate.returningResponse(response).addTasks(taskRequest)
    }

    override fun updateTasks(id: String, taskRequest: Request<Task>): Single<MessageResponse> {
        val response = MessageResponse(true, "タスクを更新しました")
        return delegate.returningResponse(response).updateTasks(id, taskRequest)
    }

    override fun removeTasks(id: String): Single<MessageResponse> {
        val response = MessageResponse(true, "タスクを削除しました")
        return delegate.returningResponse(response).removeTasks(id)
    }

}