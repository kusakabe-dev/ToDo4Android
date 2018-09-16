package com.syousa1982.todo4android.viewmodel.fragment

import android.databinding.Bindable
import com.syousa1982.todo4android.BR
import com.syousa1982.todo4android.model.constant.ViewType
import com.syousa1982.todo4android.model.entity.Task
import com.syousa1982.todo4android.viewmodel.BaseListViewModel
import com.syousa1982.todo4android.viewmodel.BaseViewModel

/**
 * タスク一覧画面 ViewModel
 */
class TaskViewModel : BaseViewModel()

/**
 * タスク一覧画面 リストアイテム ViewModel
 */
class TaskListViewModel : BaseListViewModel(){

    override val viewType: ViewType = ViewType.VIEW_TYPE_1

    override fun getItemId(): String? {
        return task?.id
    }

    /**
     * タスク
     */
    @Bindable
    var task: Task? = null
    set(value) {
        field = value
        notifyPropertyChanged(BR._all)
    }

    /**
     * タスクモデルを再生成
     * 引数の項目だけ更新して再生成する
     *
     * @param isDone 完了フラグ
     */
    fun recreateByParam(isDone: Boolean): Task? {
        task?.let {
            return Task(
                    it.id,
                    it.name,
                    isDone
            )
        }
        return null
    }
}