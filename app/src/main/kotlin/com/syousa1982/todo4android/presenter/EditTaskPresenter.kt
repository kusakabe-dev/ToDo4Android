package com.syousa1982.todo4android.presenter

import android.util.Log
import com.syousa1982.todo4android.extension.className
import com.syousa1982.todo4android.model.entity.Task
import com.syousa1982.todo4android.model.repository.ITaskRepository
import com.syousa1982.todo4android.presenter.Viewable.EditTaskViewable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * タスク編集Presenter
 *
 * @param viewable EditTaskViewable
 * @param taskRepository ITaskRepository
 */
class EditTaskPresenter(private val viewable: EditTaskViewable,
                        private val taskRepository: ITaskRepository) : BasePresenter() {

    /**
     * タスクをid指定取得
     *
     * @param id タスクID一覧
     */
    fun fetchTask(id: String) {
        repositoryStreamTasks.add(taskRepository.fetchTask(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewable.showProgress() }
                .doFinally { viewable.dismissProgress() }
                .subscribe({
                    viewable.onBindTask(it)
                }, {
                    Log.e(className(), "Taskを取得できません : $it")
                })
        )
    }

    /**
     * タスクを更新
     *
     * @param task
     */
    fun updateTask(task: Task) {
        repositoryStreamTasks.add(taskRepository.updateTasks(task)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(className(), "更新成功 : $it")
                }, {
                    viewable.onFailureUpdateTask()
                    Log.e(className(), "更新失敗 : $it")
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryStreamTasks.clear()
    }
}