package com.syousa1982.todo4android.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

/**
 * TaskEntity Entity
 */
@Entity(tableName = "tasks")
data class Task(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val status: String = TaskStatus.TODO.value
)

enum class TaskStatus(val value: String) {
    TODO("todo"),
    DONE("done")
}

class TaskStatusConverter {

    @TypeConverter
    fun fromStatus(status: TaskStatus) = status.value

    @TypeConverter
    fun toStatus(value: String) = when(value) {
        TaskStatus.TODO.value -> TaskStatus.TODO
        TaskStatus.DONE.value -> TaskStatus.DONE
        else -> throw IllegalStateException("存在しないステータス")
    }
}