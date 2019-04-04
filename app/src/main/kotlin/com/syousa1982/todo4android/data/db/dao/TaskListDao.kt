package com.syousa1982.todo4android.data.db.dao

import androidx.room.*
import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import io.reactivex.Completable
import io.reactivex.Single

/**
 * タスクリスト Dao
 */
@Dao
interface TaskListDao {

    /**
     * タスクリストを追加
     *
     * @param taskLists
     */
    @Insert
    fun insertTaskLists(vararg taskLists: TaskListEntity): Completable

    /**
     * タスクを追加
     *
     * @param tasks
     */
    @Insert
    fun insertTasks(vararg tasks: TaskEntity): Completable

    /**
     * タスクを更新
     *
     * @param tasks
     */
    @Update
    fun updateTasks(vararg tasks: TaskEntity): Completable

    /**
     * タスクリスト削除
     *
     * @param taskLists
     */
    @Delete
    fun deleteTaskLists(vararg taskLists: TaskListEntity): Completable

    /**
     * タスク削除
     *
     * @param tasks
     */
    @Delete
    fun deleteTasks(vararg tasks: TaskEntity): Completable

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