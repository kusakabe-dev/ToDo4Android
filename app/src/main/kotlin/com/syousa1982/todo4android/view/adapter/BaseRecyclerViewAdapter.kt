package com.syousa1982.todo4android.view.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.syousa1982.todo4android.viewmodel.BaseListViewModel

abstract class BaseRecyclerViewAdapter<T : BaseListViewModel> : RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder>() {

    /**
     * アイテムリスト
     */
    val items: MutableList<T> = mutableListOf()

    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * アイテムを設定
     *
     * @param items List
     */
    open fun setItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * 指定ポジションのアイテムを削除
     *
     * @param position Int
     */
    open fun removeItem(position: Int) {
        this.items.removeAt(position)
        notifyDataSetChanged()
    }

    /**
     * 共通 RecyclerView ViewHolder
     *
     * @param viewDataBinding ViewDataBinding
     */
    open class BaseViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)
}