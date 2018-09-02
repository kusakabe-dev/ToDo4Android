package com.syousa1982.todo4android.helper

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import com.syousa1982.todo4android.extension.className

object NetworkHelper {

    /**
     * ネットワークを検知する クラス
     *
     * @param networkListener ネットワークリスナー[OnNetworkListener]
     */
    class NetworkReceiver(var networkListener: OnNetworkListener? = null) : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            // ネットワークの状態が変わるとコールされます
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = cm.activeNetworkInfo
            Log.w(className(), "ネットワーク状態の変更を検知 : $activeNetwork")
            if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
                // ネットワークが接続された時
                networkListener?.onChangedNetworkState(NetworkState.CONNECT)
            } else {
                // ネットワークが切断された時
                networkListener?.onChangedNetworkState(NetworkState.DISCONNECT)
            }
        }
    }

    /**
     * ネットワークの接続状態を判定
     *
     * @param context Context
     * @return Boolean
     */
    fun isConnect(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}

interface OnNetworkListener {

    /**
     * ネットワークの接続を検知
     *
     * @param state ネットワーク状態
     */
    fun onChangedNetworkState(state: NetworkState)
}

/**
 * ネットワーク状態
 */
enum class NetworkState {
    /**
     * 接続状態
     */
    CONNECT,
    /**
     * 切断状態
     */
    DISCONNECT
}