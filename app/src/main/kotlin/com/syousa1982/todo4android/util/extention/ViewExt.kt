package com.syousa1982.todo4android.util.extention

import android.os.Handler
import android.view.View

/**
 * 連打対策のためViewを一定時間クリック無効にするクリックリスナー
 *
 * @param onClickListener Unit
 */
fun View.setOnClickPauseListener(onClickListener: (View) -> Unit) {
    if (hasOnClickListeners()) {
        return
    }
    setOnClickListener {
        pauseClickTimer()
        onClickListener.invoke(it)
    }
}

/**
 * 連打対策のためViewを一定時間クリック無効にする
 *
 * @param delayMillis クリックの無効化時間（ミリ秒）
 */
fun View.pauseClickTimer(delayMillis: Long = 500) {
    isClickable = false
    Handler().postDelayed({ isClickable = true }, delayMillis)
}