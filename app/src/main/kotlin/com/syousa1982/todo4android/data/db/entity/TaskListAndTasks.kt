package com.syousa1982.todo4android.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

/**
 * TaskとTaskListをリレーションさせるクラス
 */
class TaskListAndTasks {
    @Embedded
    lateinit var task: Task

    @Relation(parentColumn = "id", entityColumn = "taskListId")
    lateinit var tasks: List<Task>
}