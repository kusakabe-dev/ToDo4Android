package com.syousa1982.todo4android.data.db.dao

import androidx.room.*
import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import io.reactivex.Single

/**
 * タスクリスト Dao
 */
@Dao
interface TaskListDao {

    /**
     * タスクリストを追加
     * todo: Singleで取得したい
     *
     * @param taskList
     */
    @Insert
    fun insertTaskList(taskList: TaskListEntity): Single<Long>

    /**
     * タスクを追加
     *
     * @param task
     */
    @Insert
    fun insertTask(task: TaskEntity): Single<Long>

    /**
     * タスクリストを更新
     *
     * @param taskList
     */
    @Update
    fun updateTaskList(taskList: TaskListEntity): Single<Int>

    /**
     * タスクを更新
     *
     * @param task
     */
    @Update
    fun updateTask(task: TaskEntity): Single<Int>

    /**
     * タスクリスト削除
     *
     * @param taskLists
     */
    @Delete
    fun deleteTaskList(taskLists: TaskListEntity): Single<Int>

    /**
     * タスク削除
     * Todo: 複数削除することを前提に修正する
     *
     * @param tasks
     */
    @Delete
    fun deleteTask(tasks: TaskEntity): Single<Int>

    /**
     * タスクリストとタスクを取得
     */
    @Transaction
    @Query("SELECT * FROM task_list")
    fun loadTaskListAndTasks(): Single<List<TaskListAndTasks>>

    /**
     * タスクリストとタスクをID指定で取得
     *
     * @param taskListId
     */
    @Transaction
    @Query("SELECT * FROM task_list WHERE id = :taskListId")
    fun loadTaskListAndTasks(taskListId: String): Single<TaskListAndTasks>
}