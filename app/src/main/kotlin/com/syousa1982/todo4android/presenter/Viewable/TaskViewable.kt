package com.syousa1982.todo4android.presenter.Viewable

import com.syousa1982.todo4android.viewmodel.fragment.TaskListViewModel

interface TaskViewable {

    /**
     * タスクリストをViewにバインド
     *
     * @param viewModels ドクターリスト
     */
    fun onBindTasks(viewModels: List<TaskListViewModel>)
}