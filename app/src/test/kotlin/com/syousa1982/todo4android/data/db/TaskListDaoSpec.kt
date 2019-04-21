package com.syousa1982.todo4android.data.db

import android.content.Context
import androidx.room.Room
import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
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
                TaskEntity(1, 1, "hoge", "done"),
                TaskEntity(2, 1, "piyo", "todo")
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
            it("タスク追加") {
                tasks.forEach {
                    database.taskListDao().insertTask(it).test().await().assertComplete()
                }
            }
            it("タスク2件") {
                val result = database.taskListDao().loadTaskListAndTasks()
                val taskListAndTasks = TaskListAndTasks()
                taskListAndTasks.taskList = taskList
                taskListAndTasks.tasks = tasks
                result
                    .test()
                    .await()
                    .assertValue {
                        it.first().taskList.name == taskListAndTasks.taskList.name
                            && it.first().tasks.count() == taskListAndTasks.tasks.count()
                    }
            }
        }
    }
})