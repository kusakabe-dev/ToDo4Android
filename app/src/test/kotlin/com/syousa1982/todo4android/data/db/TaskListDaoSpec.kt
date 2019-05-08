package com.syousa1982.todo4android.data.db

import android.content.Context
import androidx.room.Room
import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import com.syousa1982.todo4android.domain.model.Task
import io.mockk.mockk
import org.dbtools.android.room.jdbc.JdbcSQLiteOpenHelperFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


class TaskListDaoSpec : Spek({
    describe("TaskListDao") {
        val context: Context by lazy {
            mockk<Context>()
        }

        val database: AppDatabase by lazy {
            Room.inMemoryDatabaseBuilder(
                context,
                AppDatabase::class.java
            ).allowMainThreadQueries()
                .openHelperFactory(JdbcSQLiteOpenHelperFactory())
                .build()
        }
        context("Todoリストにタスク2件追加") {
            val taskList = TaskListEntity(id = 1, name = "Todoリスト")
            val tasks = listOf(
                TaskEntity(1, 1, "hoge", Task.Status.TODO.value),
                TaskEntity(2, 1, "piyo", Task.Status.TODO.value),
                TaskEntity(3, 1, "aaaaaa", Task.Status.TODO.value)
            )
            it("タスクリスト追加") {
                database.taskListDao().insertTaskList(taskList).test().await().assertComplete()
            }
            it("タスクリストの値とタスクの件数チェック") {
                val result = database.taskListDao().loadTaskListAndTasks()
                val taskListAndTasks = TaskListAndTasks()
                taskListAndTasks.taskList = taskList
                taskListAndTasks.tasks = listOf()
                result
                    .test()
                    .await()
                    .assertValue {
                        it.first().taskList.name == taskListAndTasks.taskList.name
                            && it.first().tasks.count() == taskListAndTasks.tasks.count()
                    }
            }
            it("タスクリスト更新") {
                val updatedTaskList = TaskListEntity(id = 1, name = "更新済みTodoリスト")
                database.taskListDao().updateTaskList(updatedTaskList).test().await().assertComplete()
                val result = database.taskListDao().loadTaskListAndTasks()
                val taskListAndTasks = TaskListAndTasks()
                taskListAndTasks.taskList = updatedTaskList
                taskListAndTasks.tasks = listOf()
                result
                    .test()
                    .await()
                    .assertValue {
                        it.first().taskList.name == taskListAndTasks.taskList.name
                            && it.first().tasks.count() == taskListAndTasks.tasks.count()
                    }
            }
            it("タスク追加") {
                tasks.forEach {
                    database.taskListDao().insertTask(it).test().await().assertComplete()
                }
            }
            it("タスク取得テスト") {
                val result = database.taskListDao().loadTaskListAndTasks(taskList.id.toString())
                val taskListAndTasks = TaskListAndTasks()
                taskListAndTasks.taskList = taskList
                taskListAndTasks.tasks = tasks
                result
                    .test()
                    .await()
                    .assertValue {
                        it.tasks == taskListAndTasks.tasks
                    }
            }
            it("タスク更新") {
                val updatedTask = TaskEntity(2, 1, "piyo", Task.Status.DONE.value)
                val updatedTasks = tasks.toMutableList()
                updatedTasks.replaceAll {
                    when {
                        it.id == updatedTask.id -> updatedTask
                        else -> it
                    }
                }
                database.taskListDao().updateTask(updatedTask).test().await().assertComplete()
                val result = database.taskListDao().loadTaskListAndTasks(taskList.id.toString())
                val taskListAndTasks = TaskListAndTasks()
                taskListAndTasks.tasks = updatedTasks

                result.test().await().assertValue {
                    it.tasks.filter {
                        it.status == updatedTask.status
                    }.count() > 0
                }

            }
        }
    }
})