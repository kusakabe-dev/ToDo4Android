package com.syousa1982.todo4android.view.adapter

import android.databinding.ViewDataBinding
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.syousa1982.todo4android.extension.className
import com.syousa1982.todo4android.viewmodel.BaseListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

abstract class BaseRecyclerViewAdapter<T : BaseListViewModel> : RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder>() {

    /**
     * アイテムリスト
     */
    private val items: MutableList<T> = mutableListOf()

    /**
     * アイテム変更 Observable
     */
    private val itemDiffSubject = PublishSubject.create<List<T>>().toSerialized()

    /**
     * アイテム変更 Observable 管理
     */
    private val itemDiffDisposable = CompositeDisposable()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        itemDiffSubject.observeOn(Schedulers.computation())
                // oldとnewのPearを用意
                .map { Pair<List<T>, List<T>>(it, mutableListOf()) }
                .scan { old, new -> Pair(old.first, new.first) }
                // oldとnewの差分チェック
                .map { (oldItems, newItems) -> DiffUtil.calculateDiff(DiffUtilCallback(oldItems, newItems)) }
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext { it.dispatchUpdatesTo(this) }
                .subscribe({
                    Log.d(className(), "リスト更新成功")
                }, {
                    Log.e(className(), "リスト更新失敗 : $it")
                })
                // 更新後のリストを反映
                .also { itemDiffDisposable.add(it) }
    }


    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        itemDiffDisposable.clear()
    }

    /**
     * アイテム（複数）を追加
     *
     * @param items List
     */
    open fun addItems(items: List<T>) {
        this.items.addAll(items)
        itemDiffSubject.onNext(items)
    }

    /**
     * アイテムを設定
     *
     * @param items List
     */
    open fun setItems(items: List<T>) {
        this.items.clear()
        this.items.addAll(items)
        itemDiffSubject.onNext(items)
    }

    /**
     * アイテムをクリア
     */
    open fun clearItems() {
        this.items.clear()
        itemDiffSubject.onNext(listOf())
    }

    /**
     * 共通 RecyclerView ViewHolder
     *
     * @param viewDataBinding ViewDataBinding
     */
    open class BaseViewHolder(viewDataBinding: ViewDataBinding) : RecyclerView.ViewHolder(viewDataBinding.root)

    /**
     * リストアイテム変更コールバック
     *
     * @param oldItems 古いアイテムリスト
     * @param newItems 新しいアイテムリスト
     */
    private class DiffUtilCallback<T : BaseListViewModel>(private var oldItems: List<T>, private var newItems: List<T>) : DiffUtil.Callback() {

        override fun getOldListSize(): Int = oldItems.size

        override fun getNewListSize(): Int = newItems.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition].getItemId() == newItems[newItemPosition].getItemId()
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItems[oldItemPosition] == newItems[newItemPosition]
        }
    }
}