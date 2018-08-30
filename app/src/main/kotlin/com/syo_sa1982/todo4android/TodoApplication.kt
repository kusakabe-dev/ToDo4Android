package com.syo_sa1982.todo4android

import android.app.Activity
import android.app.Application
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.ProcessLifecycleOwner
import android.os.Bundle

/**
 * アプリケーションクラス
 */
class TodoApplication : Application(), LifecycleObserver, Application.ActivityLifecycleCallbacks {

    override fun onCreate() {
        super.onCreate()
        // Applicationのライフサイクルコールバックを登録
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
        // Activityのライフサイクルコールバックを登録
        registerActivityLifecycleCallbacks(this)


    }



    // region Activity Lifecycle

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStarted(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResumed(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityPaused(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityStopped(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityDestroyed(activity: Activity?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    // endregion
}