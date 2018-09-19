package com.syousa1982.todo4android.extension

import android.databinding.BindingAdapter
import android.os.Handler
import android.view.View


/**
 * View 拡張オブジェクト
 */
object ViewExtension {

    /**
     * Viewの表示・非表示
     *
     * @param view View
     * @param isVisible Boolean
     */
    @JvmStatic
    @BindingAdapter("visibleOrGone")
    fun setVisibleOrGone(view: View, isVisible: Boolean) {
        view.visibility = if (isVisible) View.VISIBLE else View.GONE
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