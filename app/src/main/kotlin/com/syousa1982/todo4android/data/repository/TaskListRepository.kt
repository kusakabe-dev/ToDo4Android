package com.syousa1982.todo4android.data.repository

import com.syousa1982.todo4android.data.db.dao.TaskListDao
import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import io.reactivex.Single


interface ITaskListRepository {
    // region insert
    /**
     * タスクリストを追加
     *
     * @param taskLists
     */
    fun insertTaskListByDB(taskLists: TaskListEntity): Single<Long>

    /**
     * タスクを追加
     *
     * @param task
     */
    fun insertTaskByDB(task: TaskEntity): Single<Long>
    // endregion

    // region update
    /**
     * タスクを更新
     *
     * @param task
     */
    fun updateTaskByDB(task: TaskEntity): Single<Int>
    // endregion

    // region delete
    /**
     * タスクリスト削除
     *
     * @param taskLists
     */
    fun deleteTaskListByDB(taskLists: TaskListEntity): Single<Int>

    /**
     * タスク削除
     *
     * @param task
     */
    fun deleteTaskByDB(task: TaskEntity): Single<Int>
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
    override fun insertTaskListByDB(taskList: TaskListEntity): Single<Long> {
        return dao.insertTaskList(taskList)
    }

    override fun insertTaskByDB(task: TaskEntity): Single<Long> {
        return dao.insertTask(task)
    }

    override fun updateTaskByDB(task: TaskEntity): Single<Int> {
        return dao.updateTask(task)
    }

    override fun deleteTaskByDB(task: TaskEntity): Single<Int> {
        return dao.deleteTask(task)
    }

    override fun deleteTaskListByDB(taskList: TaskListEntity): Single<Int> {
        return dao.deleteTaskList(taskList)
    }

    override fun loadTaskListAndTasksByDB(): Single<List<TaskListAndTasks>> {
        return dao.loadTaskListAndTasks()
    }

    override fun loadTaskListAndTasksByDB(taskListId: String): Single<TaskListAndTasks> {
        return dao.loadTaskListAndTasks(taskListId)
    }
}