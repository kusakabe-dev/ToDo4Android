package com.syousa1982.todo4android.domain.model

import java.io.Serializable

/**
 * タスクリスト
 *
 * @param id
 * @param name
 * @param tasks
 * @property taskCount
 */
data class TaskList(
    val id: Int,
    val name: String,
    val tasks: List<Task>
) : Serializable {
    /**
     * タスク件数
     */
    val taskCount = tasks.count()
}