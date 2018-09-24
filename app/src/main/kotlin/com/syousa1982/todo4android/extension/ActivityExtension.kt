package com.syousa1982.todo4android.extension

import android.content.Intent
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

/**
 * FragmentのPop遷移（前の画面にデータを結果を通知）
 * <p>
 * BaseFragmentのonFragmentResultで結果を受け取ります
 * </p>
 *
 * @param resultCode 結果コード
 * @param data 結果データ
 * @return Boolean 遷移したかどうか
 */
fun BaseActivity.pop(resultCode: Int, data: Intent? = null): Boolean? = fragmentController.pop(resultCode, data)