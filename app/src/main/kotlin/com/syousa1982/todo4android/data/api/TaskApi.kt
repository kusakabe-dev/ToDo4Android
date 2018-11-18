package com.syousa1982.todo4android.data.api

import com.syousa1982.todo4android.BuildConfig
import com.syousa1982.todo4android.data.api.response.ItemsResponse
import com.syousa1982.todo4android.data.api.response.TaskResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

/**
 * タスクAPI
 */
interface TaskApi {

    /**
     * タスクを取得
     */
    @Headers("x-api-key: ${BuildConfig.OPEN_TASK_API_KEY}")
    @GET("tasks")
    fun fetchTasks(): Single<ItemsResponse<TaskResponse>>

}