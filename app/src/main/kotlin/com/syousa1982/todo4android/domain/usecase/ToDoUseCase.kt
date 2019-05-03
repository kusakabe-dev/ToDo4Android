package com.syousa1982.todo4android.domain.usecase

import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import com.syousa1982.todo4android.data.repository.ITaskListRepository
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.domain.model.Task
import com.syousa1982.todo4android.domain.model.TaskList
import com.syousa1982.todo4android.domain.translator.TaskListTranslator
import com.syousa1982.todo4android.util.extention.toResult
import com.syousa1982.todo4android.util.rx.SchedulerProvider
import io.reactivex.Flowable
import io.reactivex.functions.BiFunction

/**
 * ToDo機能 インタフェース
 */
interface IToDoUseCase {

    /**
     * タスクリストのListを取得
     */
    fun getTaskLists(): Flowable<Result<List<TaskList>>>

    /**
     * タスクのListを取得
     *
     * @param taskListId
     */
    fun getTasks(taskListId: Int): Flowable<Result<List<Task>>>

    /**
     * タスクリストを追加
     *
     * @param name
     */
    fun addTaskList(name: String): Flowable<Result<Boolean>>

    /**
     * タスクを追加
     *
     * @param taskListId
     * @param name
     */
    fun addTask(taskListId: Int, name: String): Flowable<Result<Boolean>>

    /**
     * タスクリストを更新
     *
     * @param taskList
     */
    fun updateTaskList(taskList: TaskList): Flowable<Result<Boolean>>

    /**
     * タスクを更新
     *
     * @param
     */
    fun updateTask(taskListId: Int, task: Task): Flowable<Result<Boolean>>

    /**
     * タスクリストとタスクを削除
     */
    fun removeTaskListAndTasks(taskList: TaskList): Flowable<Result<Boolean>>

    /**
     * タスクリストを削除
     * memo:タスクリスト削除時はリレーションしているタスクも削除する
     *
     * @param taskList
     */
    fun removeTaskList(taskList: TaskList): Flowable<Result<Boolean>>

    /**
     * タスクを削除
     *
     * @param taskListId
     */
    fun removeTask(taskListId: Int, task: Task): Flowable<Result<Boolean>>

    /**
     * タスクを全件削除
     *
     * @param taskListId
     * @param tasks
     */
    fun removeTasks(taskListId: Int, tasks: List<Task>): Flowable<Result<Boolean>>

}

/**
 * ToDo機能
 *
 * @param repository
 * @param schedulerProvider
 */
class ToDoUseCase(private val repository: ITaskListRepository,
                  private val schedulerProvider: SchedulerProvider) : IToDoUseCase {
    override fun getTaskLists(): Flowable<Result<List<TaskList>>> {
        return repository.loadTaskListAndTasksByDB()
            .map {
                TaskListTranslator.toTodoLists(it)
            }
            .toResult(schedulerProvider)
    }

    override fun getTasks(taskListId: Int): Flowable<Result<List<Task>>> {
        return repository.loadTaskListAndTasksByDB(taskListId.toString())
            .map {
                TaskListTranslator.toTodoList(it).tasks
            }
            .toResult(schedulerProvider)
    }

    override fun addTaskList(name: String): Flowable<Result<Boolean>> {
        val entity = TaskListEntity(name = name)
        return repository.insertTaskListByDB(entity)
            .map {
                true
            }
            .toResult(schedulerProvider)
    }

    override fun addTask(taskListId: Int, name: String): Flowable<Result<Boolean>> {
        val entity = TaskEntity(taskListId = taskListId, name = name, status = Task.Status.TODO.value)
        return repository.insertTaskByDB(entity)
            .map {
                true
            }
            .toResult(schedulerProvider)
    }

    override fun updateTaskList(taskList: TaskList): Flowable<Result<Boolean>> {
        val entity = TaskListEntity(taskList.id, taskList.name)
        return repository.updateTaskListByDB(entity)
            .map { true }
            .toResult(schedulerProvider)
    }

    override fun updateTask(taskListId: Int, task: Task): Flowable<Result<Boolean>> {
        val entity = TaskEntity(task.id, taskListId, task.name, task.status.value)
        return repository.updateTaskByDB(entity)
            .map { true }
            .toResult(schedulerProvider)
    }

    override fun removeTaskListAndTasks(taskList: TaskList): Flowable<Result<Boolean>> {
        val taskListResult = removeTaskList(taskList)
        val tasksResult = removeTasks(taskList.id, taskList.tasks)
        return Flowable.zip(taskListResult, tasksResult, BiFunction {taskListResult, tasksResult ->
            when {
                (taskListResult is Result.Failure) || (tasksResult is Result.Failure) -> {
                    Result.failure(Throwable())
                }
                (taskListResult is Result.Success) && (tasksResult is Result.Success) -> {
                    Result.success(true)
                }
                else -> {
                    Result.progress()
                }
            }
        })
    }

    override fun removeTaskList(taskList: TaskList): Flowable<Result<Boolean>> {
        val entity = TaskListEntity(taskList.id, taskList.name)
        return repository.deleteTaskListByDB(entity)
            .map { true }
            .toResult(schedulerProvider)
    }

    override fun removeTask(taskListId: Int, task: Task): Flowable<Result<Boolean>> {
        val entity = TaskEntity(task.id, taskListId, task.name, task.status.value)
        return repository.deleteTaskByDB(entity)
            .map { true }
            .toResult(schedulerProvider)
    }

    override fun removeTasks(taskListId: Int, tasks: List<Task>): Flowable<Result<Boolean>> {
        val entities = tasks.map { task ->
            TaskEntity(task.id, taskListId, task.name, task.status.value)
        }
        return repository.deleteTasksByDB(entities)
            .map { true }
            .toResult(schedulerProvider)
    }
}