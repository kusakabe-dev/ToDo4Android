package com.syousa1982.todo4android.util.extention

import androidx.appcompat.widget.Toolbar

fun Toolbar.clear() {
    title = null
    subtitle = null
    navigationIcon = null
    setNavigationOnClickListener(null)
    logo = null
    menu.clear()
    setOnMenuItemClickListener(null)
}