package com.syousa1982.todo4android.presentation.tasklist

import androidx.lifecycle.MutableLiveData
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.domain.model.Task
import com.syousa1982.todo4android.domain.model.TaskList
import com.syousa1982.todo4android.presentation.BaseViewModel

/**
 * タスクリスト一覧 ViewModel
 * todo:usecaseをインジェクション
 */
class TaskListViewModel : BaseViewModel() {

    val taskLists = MutableLiveData<Result<List<TaskList>>>()

    override fun onResume() {
        super.onResume()
        taskLists.value = Result.success(
                listOf(
                        TaskList(1, "ToDo", listOf(
                                Task(1, "ほげ", Task.Status.DONE),
                                Task(2, "会社を爆破", Task.Status.TODO),
                                Task(3, "殺意駆動開発", Task.Status.TODO)
                        )),
                        TaskList(2, "家事", listOf(
                                Task(1, "飯作る", Task.Status.DONE),
                                Task(2, "皿洗う", Task.Status.DONE),
                                Task(3, "掃除", Task.Status.TODO)
                        ))
                )
        )
    }
}