package com.syousa1982.todo4android.viewmodel.fragment

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.syousa1982.todo4android.BR
import com.syousa1982.todo4android.model.entity.Task
import com.syousa1982.todo4android.viewmodel.BaseViewModel

/**
 * タスク編集 ViewModel
 */
class EditTaskViewModel : BaseViewModel() {

    val observer = Observer()

    /**
     * タスクモデルを生成
     *
     * @return Task
     */
    fun create(): Task {
        return Task(
                "",
                observer.name,
                false
        )
    }

    /**
     * ViewModelの中身をクリア
     */
    fun clear() {
        observer.name = ""
    }

    /**
     * オブザーバー
     */
    inner class Observer : BaseObservable() {

        /**
         * タスク名
         */
        @Bindable
        var name: String = ""
            set(value) {
                field = value
                notifyPropertyChanged(BR.name)
                notifyPropertyChanged(BR.enableEditButton)
            }

        /**
         * 通信中
         */
        @Bindable
        var inProgress: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.inProgress)
                notifyPropertyChanged(BR.enableEditButton)
            }

        /**
         * 追加ボタンの活性状態
         */
        @Bindable
        var enableEditButton: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.enableAddButton)
            }
            get() {
                return name.isNotEmpty() && !inProgress
            }
    }
}