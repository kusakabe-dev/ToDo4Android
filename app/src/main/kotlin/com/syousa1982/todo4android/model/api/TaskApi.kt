package com.syousa1982.todo4android.model.api

import com.syousa1982.todo4android.BuildConfig
import com.syousa1982.todo4android.model.api.response.ItemsResponse
import com.syousa1982.todo4android.model.api.response.MessageResponse
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
    @Headers("x-api-key: ${BuildConfig.OPEN_TASK_API_KEY}")
    @GET("tasks")
    fun fetchTasks(): Single<ItemsResponse<List<Task>>>

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