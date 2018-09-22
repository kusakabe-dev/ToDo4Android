package com.syousa1982.todo4android.presenter.Viewable

/**
 * タスク編集Viewインタフェース
 */
interface EditTaskViewable : ProgressViewable {

    /**
     * タスク取得時の処理
     */
    fun onBindTask()

    /**
     * タスク編集成功時の処理
     */
    fun onSuccessUpdateTask()

    /**
     * タスク編集失敗時の処理
     */
    fun onFailureUpdateTask()
}