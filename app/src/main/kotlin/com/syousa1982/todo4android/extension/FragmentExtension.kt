package com.syousa1982.todo4android.extension

import android.app.Activity
import android.content.Intent
import android.support.v4.app.Fragment
import com.syousa1982.todo4android.TodoApplication
import com.syousa1982.todo4android.model.constant.RequestCode
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
 * FragmentのPush遷移（前の画面にデータを結果を通知）
 * <p>
 * BaseFragmentのonFragmentResultで結果を受け取ります
 * </p>
 *
 * @param fragment BaseFragment
 * @param requestCode リクエストコード
 */
fun Fragment.push(fragment: BaseFragment, requestCode: RequestCode) = baseActivity()?.push(fragment, requestCode)

/**
 * FragmentのPop遷移
 *
 * @param popCount Popする数
 * @return Boolean 遷移したかどうか
 */
fun Fragment.pop(popCount: Int = 1): Boolean? = baseActivity()?.pop(popCount)

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
fun Fragment.pop(resultCode: Int, data: Intent? = null): Boolean? = baseActivity()?.pop(resultCode, data)

/**
 * FragmentからActivityを閉じる
 *
 * @param resultCode 結果値
 * @param data onActivityResultで受け取る、遷移元へ通知するデータ
 */
fun Fragment.finishActivity(resultCode: Int = Activity.RESULT_OK, data: Intent? = null) {
    val activity = activity ?: return
    activity.setResult(resultCode, data)
    activity.finish()
}

/**
 * 画面全体のProgressBarを表示
 */
fun Fragment.showScreenProgress() = baseActivity()?.showScreenProgress()

/**
 * 画面全体のProgressBarを非表示
 */
fun Fragment.dismissScreenProgress() = baseActivity()?.dismissScreenProgress()