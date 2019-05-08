package com.syousa1982.todo4android.presentation.task

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.domain.model.Task
import com.syousa1982.todo4android.domain.usecase.IToDoUseCase
import com.syousa1982.todo4android.presentation.BaseViewModel
import com.syousa1982.todo4android.util.extention.className
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

/**
 * タスク一覧 ViewModel
 */
class TaskViewModel(private val todoUseCase: IToDoUseCase) : BaseViewModel() {

    /**
     * タスク一覧
     */
    val tasks = MutableLiveData<Result<List<Task>>>()

    /**
     * タスクリストID
     */
    val taskListId = MutableLiveData<Int>()

    /**
     * 更新結果
     */
    val updateResult = MutableLiveData<Result<Boolean>>()

    override fun onResume() {
        super.onResume()
        Log.d(className(), "onResume#taskListid: ${taskListId.value}")
        getTasks()
    }

    /**
     * タスクを取得
     */
    fun getTasks() {
        val taskListId = taskListId.value ?: throw IllegalAccessException("タスクリストのIDが指定されていません")
        todoUseCase.getTasks(taskListId).subscribeBy(
            onNext = { tasks.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }

    /**
     * タスクを更新
     */
    fun updateTask(task: Task) {
        val taskListId = taskListId.value ?: throw IllegalAccessException("タスクリストのIDが指定されていません")
        val updateTask = Task(
            task.id,
            task.name,
            Task.Status.changeStatus(task.status)
        )
        todoUseCase.updateTask(taskListId, updateTask).subscribeBy(
            onNext = { updateResult.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }

    /**
     * タスクを削除
     */
    fun delete(task: Task) {
        val taskListId = taskListId.value ?: throw IllegalAccessException("タスクリストのIDが指定されていません")
        todoUseCase.removeTask(taskListId, task).subscribeBy(
            onNext = { updateResult.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }

}