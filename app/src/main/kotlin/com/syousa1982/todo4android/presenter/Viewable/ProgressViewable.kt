package com.syousa1982.todo4android.presenter.Viewable

/**
 * プログレス表示 Viewインタフェース
 */
interface ProgressViewable {

    /**
     * プログレス表示
     */
    fun showProgress()

    /**
     * プログレス非表示
     */
    fun dismissProgress()
}