package com.syousa1982.todo4android.model.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.squareup.moshi.Moshi
import com.squareup.moshi.Rfc3339DateJsonAdapter
import com.syousa1982.todo4android.BuildConfig
import com.syousa1982.todo4android.model.Task
import com.syousa1982.todo4android.model.api.TaskApi
import com.syousa1982.todo4android.model.request.AddTaskRequest
import com.syousa1982.todo4android.model.response.ItemsResponse
import com.syousa1982.todo4android.model.response.MessageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

class TaskRepository(private val api: TaskApi) {

    /**
     * タスク一覧を取得
     *
     * @return
     */
    fun fetchTasks(): LiveData<List<Task>> {
        val data = MutableLiveData<List<Task>>()
        api.fetchTasks().enqueue(object : Callback<ItemsResponse<List<Task>>> {

            override fun onResponse(call: Call<ItemsResponse<List<Task>>>?, response: Response<ItemsResponse<List<Task>>>?) {
                response?.body()?.let {
                    data.value = it.items
                }
            }

            override fun onFailure(call: Call<ItemsResponse<List<Task>>>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        return data
    }

    fun addTask(request: AddTaskRequest): LiveData<String> {
        val data = MutableLiveData<String>()
        api.addTasks(request).enqueue(object : Callback<MessageResponse> {

            override fun onResponse(call: Call<MessageResponse>?, response: Response<MessageResponse>?) {
                response?.body()?.let {
                    data.value = it.message
                }
            }

            override fun onFailure(call: Call<MessageResponse>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
        return data
    }

    companion object {
        fun newInstance(): TaskRepository {

            val converter = Moshi.Builder()
                    .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                    .build()

            val retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create(converter))
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .build()
            return TaskRepository(retrofit.create(TaskApi::class.java))
        }
    }
}