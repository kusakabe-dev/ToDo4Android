package com.syousa1982.todo4android.presenter

import android.util.Log
import com.syousa1982.todo4android.extension.className
import com.syousa1982.todo4android.model.entity.Task
import com.syousa1982.todo4android.model.repository.ITaskRepository
import com.syousa1982.todo4android.presenter.Viewable.TaskViewable
import com.syousa1982.todo4android.viewmodel.fragment.TaskListViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * タスクPresenter
 *
 * @param viewable TaskViewable
 * @param taskRepository ITaskRepository
 */
class TaskPresenter(private val viewable: TaskViewable,
                    private val taskRepository: ITaskRepository) : BasePresenter() {

    /**
     * タスク一覧を取得
     */
    fun fetchTasks() {
        repositoryStreamTasks.add(taskRepository.fetchTasks()
                .toObservable()
                .map { it.sortedBy { it.id }.map { TaskListViewModel().apply { task = it } } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { viewable.showProgress() }
                .doFinally { viewable.dismissProgress() }
                .subscribe({
                    viewable.onBindTasks(it)
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

    /**
     * タスクを削除
     */
    fun removeTask(viewModel: TaskListViewModel, position: Int) {
        viewModel.task?.let {
            repositoryStreamTasks.add(taskRepository.removeTasks(it.id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        Log.d(className(), "削除成功 : $it")
                        viewable.onSuccessRemoveTask(position)
                    }, {
                        Log.e(className(), "削除失敗 : $it")
                        viewable.onFailureUpdateTask()
                    })
            )
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryStreamTasks.clear()
    }
}