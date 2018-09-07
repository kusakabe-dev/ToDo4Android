package com.syousa1982.todo4android.extension

import android.support.v4.app.Fragment
import com.syousa1982.todo4android.TodoApplication

/**
 * Fragment拡張
 */
object FragmentExtension

/**
 * アプリケーションクラスを取得
 *
 * @return TodoApplication
 */
fun Fragment.application(): TodoApplication = (activity?.application as TodoApplication)