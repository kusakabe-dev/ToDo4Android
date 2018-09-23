package com.syousa1982.todo4android.model.constant


/**
 * Activityのリクエストコード定義
 * <p>
 * 重複すると思わぬ誤作動を起こしかけないので、enumでユニークにしておく
 * </p>
 */
enum class RequestCode {

    /**
     * ネットワークエラー
     */
    NETWORK_ERROR,

    /**
     * タスク追加
     */
    TASK_ADDED,

    /**
     * タスク編集
     */
    TASK_EDIT
}