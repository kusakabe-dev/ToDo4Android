package com.syousa1982.todo4android.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * TaskEntity Entity
 */
@Entity(tableName = "task",
    foreignKeys = arrayOf(ForeignKey(
        entity = TaskListEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("taskListId"),
        onDelete = ForeignKey.CASCADE
    ))
)
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val taskListId: Int,
    val name: String,
    val status: String
)