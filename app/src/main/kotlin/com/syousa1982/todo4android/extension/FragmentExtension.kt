package com.syousa1982.todo4android.extension

import android.support.v4.app.Fragment
import com.syousa1982.todo4android.TodoApplication
import com.syousa1982.todo4android.view.activity.BaseActivity
import com.syousa1982.todo4android.view.fragment.BaseFragment

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

/**
 * 抽象Activityクラスを取得
 *
 * @return BaseActivity
 */
fun Fragment.baseActivity(): BaseActivity? = (activity as? BaseActivity)

/**
 * FragmentのPush遷移
 *
 * @param fragment BaseFragment
 */
fun Fragment.push(fragment: BaseFragment) = baseActivity()?.push(fragment)

/**
 * FragmentのPop遷移
 *
 * @param popCount Popする数
 * @return Boolean 遷移したかどうか
 */
fun Fragment.pop(popCount: Int = 1): Boolean? = baseActivity()?.pop(popCount)