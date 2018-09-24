package com.syousa1982.todo4android.model.repository

import com.syousa1982.todo4android.model.api.TaskApi
import com.syousa1982.todo4android.model.api.request.Request
import com.syousa1982.todo4android.model.api.response.MessageResponse
import com.syousa1982.todo4android.model.entity.Task
import io.reactivex.Single

class MockTaskRepository(private val api: TaskApi) : ITaskRepository {

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

    override fun fetchTask(id: String): Single<Task> {
        return Single.create { subscriber ->
            api.fetchTask(id)
                    .map {
                        it.item
                    }
                    .subscribe({
                        subscriber.onSuccess(it)
                    }, {
                        subscriber.onError(it)
                    })
        }
    }

    override fun addTasks(task: Task): Single<MessageResponse> {
        return api.addTasks(Request(task))
    }

    override fun updateTasks(task: Task): Single<MessageResponse> {
        return api.updateTasks(task.id, Request(task))
    }

    override fun removeTasks(id: String): Single<MessageResponse> {
        return api.removeTasks(id)
    }
}