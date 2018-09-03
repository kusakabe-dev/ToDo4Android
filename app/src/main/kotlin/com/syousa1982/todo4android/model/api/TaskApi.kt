package com.syousa1982.todo4android.model.api

import com.syousa1982.todo4android.model.entity.Task
import io.reactivex.Single
import retrofit2.http.*

/**
 * タスクAPI
 */
interface TaskApi {

    /**
     * タスク一覧を取得
     *
     * @return
     */
    @GET("tasks")
    fun fetchTasks(): Single<List<Task>>

    /**
     * タスクを登録
     */
    @POST("tasks")
    fun addTasks(@Body task: Task): Single<MessageResponse>

    /**
     * タスクを更新
     */
    @PUT("tasks")
    fun updateTasks(@Body task: Task):Single<MessageResponse>
}