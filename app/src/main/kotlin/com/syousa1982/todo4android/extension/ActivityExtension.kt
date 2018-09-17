package com.syousa1982.todo4android.extension

import com.syousa1982.todo4android.view.activity.BaseActivity
import com.syousa1982.todo4android.view.fragment.BaseFragment

object ActivityExtension

/**
 * FragmentのPush遷移
 *
 * @param fragment BaseFragment
 */
fun BaseActivity.push(fragment: BaseFragment) = fragmentController.push(fragment)

/**
 * FragmentのPop遷移
 *
 * @param popCount Popする数
 * @return Boolean 遷移したかどうか
 */
fun BaseActivity.pop(popCount: Int = 1): Boolean? = fragmentController.pop(popCount)