package com.syousa1982.todo4android

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ProcessLifecycleOwner
import android.os.Bundle
import android.util.Log
import com.syousa1982.todo4android.extension.className
import com.syousa1982.todo4android.helper.NetworkHelper
import com.syousa1982.todo4android.helper.NetworkState
import com.syousa1982.todo4android.helper.OnNetworkListener
import com.syousa1982.todo4android.manager.IRepositoryManager
import com.syousa1982.todo4android.manager.RepositoryManager
import com.syousa1982.todo4android.view.activity.BaseActivity
import java.lang.ref.WeakReference

/**
 * アプリケーションクラス
 */
class TodoApplication : Application(), LifecycleObserver, Application.ActivityLifecycleCallbacks, OnNetworkListener {


    /**
     * リポジトリ管理クラス
     */
    val repository: IRepositoryManager = RepositoryManager(this)

    /**
     * ネットワーク検知クラス
     */
    private val networkReceiver = NetworkHelper.NetworkReceiver()

    /**
     * 現在のActivity
     */
    private var currentActivity: WeakReference<BaseActivity?>? = null

    override fun onCreate() {
        super.onCreate()
        // Applicationのライフサイクルコールバックを登録
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        // Activityのライフサイクルコールバックを登録
        registerActivityLifecycleCallbacks(this)


    }


    // region Activity Lifecycle

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        Log.d(className(), "onActivityCreated : ${className(activity)}")
    }

    override fun onActivityStarted(activity: Activity?) {
        Log.d(className(), "onActivityStarted : ${className(activity)}")
        currentActivity = WeakReference(activity as? BaseActivity)
    }

    override fun onActivityResumed(activity: Activity?) {
        Log.d(className(), "onActivityResumed : ${className(activity)}")
    }

    override fun onActivityPaused(activity: Activity?) {
        Log.d(className(), "onActivityPaused : ${className(activity)}")
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        Log.d(className(), "onActivitySaveInstanceState : ${className(activity)}")
    }

    override fun onActivityStopped(activity: Activity?) {
        Log.d(className(), "onActivityStopped : ${className(activity)}")
    }

    override fun onActivityDestroyed(activity: Activity?) {
        Log.d(className(), "onActivityDestroyed : ${className(activity)}")
    }

    // endregion

    override fun onChangedNetworkState(state: NetworkState) {
        // ネットワーク状態を検知して、Activityに通知する
        try {
            val activity = currentActivity?.get() ?: return
            when (state) {
                NetworkState.CONNECT -> activity.onNetworkConnected()
                NetworkState.DISCONNECT -> activity.onNetworkDisconnected()
            }
        } catch (e: Exception) {
            Log.e(className(), "onChangedNetworkState : $e")
        }
    }
    // endregion
}