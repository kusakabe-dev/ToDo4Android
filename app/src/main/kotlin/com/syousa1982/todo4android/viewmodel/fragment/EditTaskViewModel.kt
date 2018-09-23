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
    fun recreate(): Task? {
        observer.task?.let {
            return Task(
                    it.id,
                    observer.name,
                    it.isDone
            )
        }
        return null
    }

    /**
     * ViewModelの中身をクリア
     */
    fun clear() {
        observer.task = null
        observer.name = ""
    }

    /**
     * タスクデータを各プロパティに代入
     */
    fun setTask(task: Task) {
        observer.task = task
        observer.name = task.name
    }

    /**
     * オブザーバー
     */
    inner class Observer : BaseObservable() {

        @Bindable
        var task: Task? = null
            set(value) {
                field = value
                notifyPropertyChanged(BR.name)
                notifyPropertyChanged(BR.enableEditButton)
            }

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