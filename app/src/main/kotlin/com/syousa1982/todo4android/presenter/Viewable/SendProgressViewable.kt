package com.syousa1982.todo4android.presenter.Viewable


/**
 * 追加または更新時のプログレス表示
 */
interface SendProgressViewable {

    /**
     * プログレス表示
     */
    fun showSendProgress()

    /**
     * プログレス非表示
     */
    fun dismissSendProgress()
}