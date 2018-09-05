package com.syousa1982.todo4android.presenter

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter {

    /**
     * リポジトリのタスクを管理
     * <p>
     * リポジトリへのアクセスはリアクティブを使用しており、Presenterが破棄されたタイミングでストリームを解放するために用意しています
     * </p>
     */
    protected var repositoryStreamTasks = CompositeDisposable()

    /**
     * onCreateライフサイクル
     * <p>
     * Presenterを作成
     * </p>
     */
    open fun onCreate() {}

    /**
     * onResumeライフサイクル
     * <p>
     * Presenterを開始
     * </p>
     */
    open fun onResume() {}

    /**
     * onPauseライフサイクル
     * <p>
     * Presenterを停止
     * </p>
     */
    open fun onPause() {}

    /**
     * onDestroyライフサイクル
     * <p>
     * Presenterを破棄
     * </p>
     */
    open fun onDestroy() {}
}