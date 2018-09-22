package com.syousa1982.todo4android.model.api

import com.syousa1982.todo4android.BuildConfig
import com.syousa1982.todo4android.model.api.request.Request
import com.syousa1982.todo4android.model.api.response.ItemResponse
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
     * タスクを取得
     *
     * @return
     */
    @Headers("x-api-key: ${BuildConfig.OPEN_TASK_API_KEY}")
    @GET("tasks/{id}")
    fun fetchTask(@Path("id") id: String): Single<ItemResponse<Task>>

    /**
     * タスクを登録
     */
    @Headers("x-api-key: ${BuildConfig.OPEN_TASK_API_KEY}")
    @POST("tasks")
    fun addTasks(@Body taskRequest: Request<Task>): Single<MessageResponse>

    /**
     * タスクを更新
     */
    @Headers("x-api-key: ${BuildConfig.OPEN_TASK_API_KEY}")
    @PATCH("tasks/{id}")
    fun updateTasks(@Path("id") id: String, @Body taskRequest: Request<Task>): Single<MessageResponse>
}