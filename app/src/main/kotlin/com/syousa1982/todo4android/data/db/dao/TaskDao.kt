package com.syousa1982.todo4android.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.syousa1982.todo4android.data.db.entity.Task

/**
 * タスク Dao
 */
@Dao
interface TaskDao {

    @Insert
    suspend fun insert(task: Task)

    @Query("SELECT * FROM tasks")
    fun getAll(): LiveData<List<Task>>
}