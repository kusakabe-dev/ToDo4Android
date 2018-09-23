package com.syousa1982.todo4android.presenter

import android.util.Log
import com.syousa1982.todo4android.extension.className
import com.syousa1982.todo4android.model.entity.Task
import com.syousa1982.todo4android.model.repository.ITaskRepository
import com.syousa1982.todo4android.presenter.Viewable.AddTaskViewable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * タスク追加Presenter
 *
 * @param viewable AddTaskViewable
 * @param taskRepository ITaskRepository
 */
class AddTaskPresenter(private val viewable: AddTaskViewable,
                       private val taskRepository: ITaskRepository) : BasePresenter() {

    /**
     * タスクを追加
     */
    fun addTask(task: Task) {
        repositoryStreamTasks.add(taskRepository.addTasks(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewable.showProgress() }
                .doFinally { viewable.dismissProgress() }
                .subscribe({
                    viewable.onSuccessAddTask()
                    Log.d(className(), "追加成功 : $it")
                }, {
                    viewable.onFailureAddTask()
                    Log.e(className(), "追加失敗 : $it")
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryStreamTasks.clear()
    }
}