package com.syousa1982.todo4android.viewmodel.fragment

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.syousa1982.todo4android.BR
import com.syousa1982.todo4android.model.entity.Task
import com.syousa1982.todo4android.viewmodel.BaseViewModel

class AddTaskViewModel : BaseViewModel() {

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
                notifyPropertyChanged(BR.enableAddButton)
            }

        /**
         * 通信中
         */
        @Bindable
        var inProgress: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.inProgress)
                notifyPropertyChanged(BR.enableAddButton)
            }

        /**
         * 追加ボタンの活性状態
         */
        @Bindable
        var enableAddButton: Boolean = false
            set(value) {
                field = value
                notifyPropertyChanged(BR.enableAddButton)
            }
            get() {
                return name.isNotEmpty() && !inProgress
            }
    }
}