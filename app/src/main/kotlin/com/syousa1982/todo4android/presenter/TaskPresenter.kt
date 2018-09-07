package com.syousa1982.todo4android.presenter

import android.util.Log
import com.syousa1982.todo4android.extension.className
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

    fun fetchTasks(){
        repositoryStreamTasks.add(taskRepository.fetchTasks()
                .toObservable()
                .map { it.map { TaskListViewModel().apply { task = it } } }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewable.onBindTasks(it)
                },{
                    Log.e(className(), "Taskを取得できません : $it")
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryStreamTasks.clear()
    }
}