package com.syousa1982.todo4android.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * TaskListEntity
 */
@Entity(tableName = "task_list")
data class TaskListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)