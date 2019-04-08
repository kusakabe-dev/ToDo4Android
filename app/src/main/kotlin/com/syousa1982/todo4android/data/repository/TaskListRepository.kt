package com.syousa1982.todo4android.data.repository

import com.syousa1982.todo4android.data.db.dao.TaskListDao
import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import io.reactivex.Completable
import io.reactivex.Single


interface ITaskListRepository {
    // region insert
    /**
     * タスクリストを追加
     *
     * @param taskLists
     */
    fun insertTaskListsByDB(taskLists: TaskListEntity): Completable

    /**
     * タスクを追加
     *
     * @param tasks
     */
    fun insertTasksByDB(tasks: TaskEntity): Completable
    // endregion

    // region update
    /**
     * タスクを更新
     *
     * @param tasks
     */
    fun updateTasksByDB(tasks: TaskEntity): Completable
    // endregion

    // region delete
    /**
     * タスクリスト削除
     *
     * @param taskLists
     */
    fun deleteTaskListsByDB(taskLists: TaskListEntity): Completable

    /**
     * タスク削除
     *
     * @param tasks
     */
    fun deleteTasksByDB(tasks: TaskEntity): Completable
    // endregion

    // region query
    /**
     * タスクリストとタスクを取得
     */
    fun loadTaskListAndTasksByDB(): Single<List<TaskListAndTasks>>

    /**
     * タスクリストとタスクをID指定で取得
     *
     * @param taskListId
     */
    fun loadTaskListAndTasksByDB(taskListId: String): Single<TaskListAndTasks>
    // endregion
}

class TaskListRepository(private val dao: TaskListDao) : ITaskListRepository {
    override fun insertTaskListsByDB(taskLists: TaskListEntity): Completable {
        return dao.insertTaskLists(taskLists)
    }

    override fun insertTasksByDB(tasks: TaskEntity): Completable {
        return dao.insertTasks(tasks)
    }

    override fun updateTasksByDB(tasks: TaskEntity): Completable {
        return dao.updateTasks(tasks)
    }

    override fun deleteTasksByDB(tasks: TaskEntity): Completable {
        return dao.deleteTasks(tasks)
    }

    override fun deleteTaskListsByDB(taskLists: TaskListEntity): Completable {
        return dao.deleteTaskLists()
    }

    override fun loadTaskListAndTasksByDB(): Single<List<TaskListAndTasks>> {
        return dao.loadTaskListAndTasks()
    }

    override fun loadTaskListAndTasksByDB(taskListId: String): Single<TaskListAndTasks> {
        return dao.loadTaskListAndTasks(taskListId)
    }
}