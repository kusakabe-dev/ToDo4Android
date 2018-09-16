package com.syousa1982.todo4android.model.repository

import com.syousa1982.todo4android.model.api.response.MessageResponse
import com.syousa1982.todo4android.model.api.TaskApi
import com.syousa1982.todo4android.model.api.request.Request
import com.syousa1982.todo4android.model.entity.Task
import io.reactivex.Single

/**
 * タスク Repositoryインタフェース
 */
interface ITaskRepository {

    /**
     * タスクを取得
     */
    fun fetchTasks(): Single<List<Task>>

    /**
     * タスクを登録
     *
     * @param task タスク
     */
    fun addTasks(task: Task):Single<MessageResponse>

    /**
     * タスクを更新
     *
     * @param task タスク
     */
    fun updateTasks(id: String, task: Task): Single<MessageResponse>

}

/**
 * タスク Repository
 */
class TaskRepository(private val api: TaskApi) : ITaskRepository {

    override fun fetchTasks(): Single<List<Task>> {
        return Single.create { subscriber ->
            api.fetchTasks()
                    .map {
                        it.items
                    }
                    .subscribe({
                        subscriber.onSuccess(it)
                    }, {
                        subscriber.onError(it)
                    })
        }
    }

    override fun addTasks(task: Task): Single<MessageResponse> {
        return api.addTasks(task)
    }

    override fun updateTasks(id: String, task: Task): Single<MessageResponse> {
        val request = Request(task)
        return api.updateTasks(id, request)
    }
}