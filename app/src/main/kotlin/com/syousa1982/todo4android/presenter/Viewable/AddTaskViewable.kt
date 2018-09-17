package com.syousa1982.todo4android.presenter.Viewable

interface AddTaskViewable {

    /**
     * タスク追加成功時の処理
     */
    fun onSuccessAddTask()

    /**
     * タスク追加失敗時の処理
     */
    fun onFailureAddTask()

}