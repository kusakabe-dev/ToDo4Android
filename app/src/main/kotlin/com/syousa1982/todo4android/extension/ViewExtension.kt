package com.syousa1982.todo4android.extension

import android.os.Handler
import android.view.View


/**
 * View 拡張オブジェクト
 */
object ViewExtension

/**
 * 連打対策のためViewを一定時間クリック無効にする
 *
 * @param delayMillis クリックの無効化時間（ミリ秒）
 */
fun View.pauseClickTimer(delayMillis: Long = 500) {
    isClickable = false
    Handler().postDelayed({ isClickable = true }, delayMillis)
}