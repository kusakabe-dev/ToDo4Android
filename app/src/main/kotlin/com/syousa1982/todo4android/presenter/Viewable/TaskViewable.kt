package com.syousa1982.todo4android.presenter.Viewable

import com.syousa1982.todo4android.viewmodel.fragment.TaskListViewModel

interface TaskViewable : ProgressViewable {

    /**
     * タスクリストをViewにバインド
     *
     * @param viewModels ドクターリスト
     */
    fun onBindTasks(viewModels: List<TaskListViewModel>)

    /**
     * タスク更新失敗時の処理
     */
    fun onFailureUpdateTask()
}