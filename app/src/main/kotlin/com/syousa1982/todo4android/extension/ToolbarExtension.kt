package com.syousa1982.todo4android.extension

import android.support.v7.widget.Toolbar


/**
 * Toolbar拡張オブジェクト
 */
object ToolbarExtension

/**
 * Toolbarをクリア
 */
fun Toolbar.clear() {
    title = null
    subtitle = null
    navigationIcon = null
    setNavigationOnClickListener(null)
    logo = null
    menu.clear()
    setOnMenuItemClickListener(null)
}