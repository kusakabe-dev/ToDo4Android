package com.syousa1982.todo4android.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * TaskList Entity
 */
@Entity(tableName = "task_list")
data class TaskList(
        @PrimaryKey(autoGenerate = true)
        val id: Int,
        val name: String
)