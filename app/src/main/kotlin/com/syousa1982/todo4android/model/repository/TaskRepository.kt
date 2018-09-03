package com.syousa1982.todo4android.model.repository

import com.syousa1982.todo4android.model.api.MessageResponse
import com.syousa1982.todo4android.model.api.TaskApi
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
    fun updateTasks(task: Task): Single<MessageResponse>

}

/**
 * タスク Repository
 */
class TaskRepository(private val api: TaskApi) : ITaskRepository {

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