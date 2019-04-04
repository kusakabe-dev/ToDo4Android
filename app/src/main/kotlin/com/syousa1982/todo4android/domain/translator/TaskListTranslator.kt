package com.syousa1982.todo4android.domain.translator

import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.domain.model.Task
import com.syousa1982.todo4android.domain.model.TaskList

/**
 * TaskList Translator
 */
object TaskListTranslator {

    /**
     * ドメインモデル 生成
     *
     * @param entity
     */
    fun toTodoList(entity: TaskListAndTasks): TaskList = TaskList(
            entity.taskList.id,
            entity.taskList.name,
            entity.tasks.map {
                Task(
                        it.id,
                        it.name,
                        Task.Status.valueOf(it.status.toUpperCase())
                )
            }
    )

    /**
     * ドメインモデルリスト 生成
     *
     * @param entities
     */
    fun toTodoLists(entities: List<TaskListAndTasks>): List<TaskList> = entities.map {
        toTodoList(it)
    }
}