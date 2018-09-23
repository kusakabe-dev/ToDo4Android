package com.syousa1982.todo4android.presenter.Viewable

import com.syousa1982.todo4android.model.entity.Task

/**
 * タスク編集Viewインタフェース
 */
interface EditTaskViewable : ProgressViewable, SendProgressViewable {

    /**
     * タスク取得時の処理
     */
    fun onBindTask(task: Task)

    /**
     * タスク編集成功時の処理
     */
    fun onSuccessUpdateTask()

    /**
     * タスク編集失敗時の処理
     */
    fun onFailureUpdateTask()
}