package com.syousa1982.todo4android.data.db.dao

import androidx.room.*
import com.syousa1982.todo4android.data.db.entity.Task
import com.syousa1982.todo4android.data.db.entity.TaskList
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
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
    fun insertTaskLists(vararg taskLists: TaskList): Completable

    /**
     * タスクを追加
     *
     * @param tasks
     */
    @Insert
    fun insertTasks(vararg tasks: Task): Completable

    /**
     * タスクを更新
     *
     * @param tasks
     */
    @Update
    fun updateTasks(vararg tasks: Task): Completable

    /**
     * タスクリスト削除
     */
    @Delete
    fun deleteTaskLists(vararg taskLists: TaskList): Completable

    /**
     * タスク削除
     */
    @Delete
    fun deleteTasks(vararg tasks: Task): Completable

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
    fun loadTaskListAndTasksById(taskListId: String): Single<List<TaskListAndTasks>>
}