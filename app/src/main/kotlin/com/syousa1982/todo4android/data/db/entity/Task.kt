package com.syousa1982.todo4android.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey

/**
 * Task Entity
 */
@Entity(tableName = "task",
        foreignKeys = arrayOf(ForeignKey(
                entity = TaskList::class,
                parentColumns = arrayOf("id"),
                childColumns = arrayOf("taskListId"),
                onDelete = ForeignKey.CASCADE
        ))
)
data class Task(
        val id: Int,
        val taskListId: Int,
        val name: String,
        val status: String
)