package com.syousa1982.todo4android.presenter.Viewable

/**
 * タスク編集Viewインタフェース
 */
interface EditTaskViewable : ProgressViewable {

    /**
     * タスク編集成功時の処理
     */
    fun onSuccessEditTask()

    /**
     * タスク編集失敗時の処理
     */
    fun onFailureEditTask()
}