package com.syousa1982.todo4android.view.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.syousa1982.todo4android.extension.className
import com.syousa1982.todo4android.model.constant.RequestCode
import com.syousa1982.todo4android.view.FragmentController
import com.syousa1982.todo4android.view.fragment.BaseFragment

/**
 * 抽象 Activity クラス
 * <p>
 * Activityに継承する
 * </p>
 */
abstract class BaseActivity : AppCompatActivity(), FragmentController.RootFragmentListener, FragmentController.TransitionFragmentListener {

    /**
     * Fragment管理クラス
     */
    val fragmentController = FragmentController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentController.onCreate(this, savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle?, outPersistentState: PersistableBundle?) {
        super.onSaveInstanceState(outState, outPersistentState)
        fragmentController.onSaveInstanceState(outState)
    }

    override fun onBackPressed() {
        // todo: バックキーをロックする必要がある場合はここに実装する
        // FragmentをPopする
        if (fragmentController.pop()) {
            return
        }
        // ActivityをFinishする
        super.finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == RequestCode.NETWORK_ERROR.ordinal) {
            onNetworkReconnected()
        }
        // Fragmentにも結果を通知する
        fragmentController.current()?.onFragmentResult(requestCode, resultCode, data)
    }

    override fun getRootFragmentCount(): Int {
        return 1
    }

    override fun onFragmentTabTransaction(fragment: BaseFragment, index: Int) {
        Log.d(className(), "onFragmentTabTransaction : ${className(fragment)}, index : $index")
    }

    override fun onFragmentTransaction(fragment: BaseFragment) {
        Log.d(className(), "onFragmentTransaction : ${className(fragment)}")
    }

    /**
     * ネットワークが接続された場合に呼ばれる
     * <p>
     * アプリケーションクラスから呼ばれる
     * </p>
     */
    open fun onNetworkConnected() {
        Log.d(className(), "ネットワークが接続されました")
        // Fragmentに通知
        fragmentController.current()?.onNetworkConnected()
    }

    /**
     * ネットワークが切断された場合に呼ばれる
     * <p>
     * アプリケーションクラスから呼ばれる
     * </p>
     */
    open fun onNetworkDisconnected() {
        Log.d(className(), "ネットワークが切断されました")
        if (this is NetworkErrorActivity) {
            Log.e(className(), "すでにネットワークエラー画面が開いています")
            return
        }
        // Fragmentに通知
        fragmentController.current()?.onNetworkDisconnected()
        // ネットワークエラー画面を開く
        startActivityForResult(NetworkErrorActivity.newIntent(this), RequestCode.NETWORK_ERROR.ordinal)
    }

    /**
     * ネットワークが再接続された場合に呼ばれる
     * <p>
     * onActivityResultから呼ばれる
     * </p>
     */
    open fun onNetworkReconnected() {
        Log.d(className(), "ネットワークが再接続されました")
        // Fragmentに通知
        fragmentController.current()?.onNetworkReconnected()
    }

    /**
     * 画面全体のProgressBarを表示
     * <p>
     * オーバーライドして使用する
     * </p>
     */
    open fun showScreenProgress() {
        Log.d(className(), "showScreenProgress")
    }

    /**
     * 画面全体のProgressBarを非表示
     * <p>
     * オーバーライドして使用する
     * </p>
     */
    open fun dismissScreenProgress() {
        Log.d(className(), "dismissScreenProgress")
    }

}