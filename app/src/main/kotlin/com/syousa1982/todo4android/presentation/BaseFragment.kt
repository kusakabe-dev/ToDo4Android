package com.syousa1982.todo4android.presentation

import android.content.Intent
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    /**
     * バックキーが押された時の制御
     * <p>
     * 必要に応じてオーバーライドする
     * </p>
     *
     * @return Boolean true:Pop処理有効 false:Pop処理無効
     */
    open fun onBackPressed(): Boolean = true

    /**
     * FragmentのPop遷移時の前画面に結果を通知
     * <p>
     * 必要に応じてオーバーライドする
     * </p>
     *
     * @param requestCode リクエストコード
     * @param resultCode 結果コード
     * @param data 結果データ
     */
    open fun onFragmentResult(requestCode: Int, resultCode: Int, data: Intent?) = Unit

    /**
     * ネットワークが接続された場合に呼ばれる
     */
    open fun onNetworkConnected() = Unit

    /**
     * ネットワークが切断された場合に呼ばれる
     */
    open fun onNetworkDisconnected() = Unit

    /**
     * ネットワークが再接続された場合に呼ばれる
     */
    open fun onNetworkReconnected() = Unit
}