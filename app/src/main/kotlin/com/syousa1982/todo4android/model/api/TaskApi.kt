package com.syousa1982.todo4android.model.api

import com.syousa1982.todo4android.BuildConfig
import com.syousa1982.todo4android.model.Task
import com.syousa1982.todo4android.model.request.AddTaskRequest
import com.syousa1982.todo4android.model.response.ItemsResponse
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TaskApi {

    /**
     * タスク一覧を取得
     */
    @Headers("x-api-key: ${BuildConfig.OPEN_TASK_API_KEY}")
    @GET("tasks")
    fun fetchTasks(): Single<ItemsResponse<List<Task>>>


    /**
     * タスクを追加
     */
    @Headers("x-api-key: ${BuildConfig.OPEN_TASK_API_KEY}")
    @POST("tasks")
    fun addTasks(@Body request: AddTaskRequest)

}