package com.syousa1982.todo4android.viewmodel

import android.databinding.BaseObservable
import com.syousa1982.todo4android.model.constant.ViewType

/**
 * 抽象 リストアイテム ViewModel
 * <p>
 * BaseRecyclerViewAdapterのアイテムに継承する
 * </p>
 */
abstract class BaseListViewModel : BaseObservable() {

    /**
     * ViewType
     */
    abstract val viewType: ViewType

    /**
     * リストの更新時に判別するためのID
     * <p>
     * リストごとにユニークな文字列を設定する
     * </p>
     *
     * @return String
     */
    abstract fun getItemId(): String?
}