package com.syousa1982.todo4android.domain.model

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
) {
    /**
     * タスク件数
     */
    val taskCount = tasks.count()
}