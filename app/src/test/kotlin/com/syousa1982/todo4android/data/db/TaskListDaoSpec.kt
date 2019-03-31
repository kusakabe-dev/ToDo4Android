package com.syousa1982.todo4android.data.db

import android.content.Context
import androidx.room.Room
import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import io.mockk.mockk
import org.dbtools.android.room.jdbc.JdbcSQLiteOpenHelperFactory
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

class TaskListDaoSpec : Spek({
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

    Feature("TaskListDao") {
        Scenario("TaskDatabase") {

            Given("Todoリストにタスク2件") {
                val taskList = TaskListEntity(1, "Todoリスト")
                val tasks = listOf(
                        TaskEntity(1, 1, "hoge", "done"),
                        TaskEntity(2, 1, "piyo", "todo")
                )
                database.taskListDao().insertTaskLists(taskList).test().await().assertComplete()
                tasks.forEach {
                    database.taskListDao().insertTasks(it).test().await().assertComplete()
                }
            }

            Then("タスクリスト1件") {
                val result = database.taskListDao().loadTaskListAndTasks()
                result.map {
                    it.count()
                }.test().await().assertValue(1)
            }

            Then("タスク2件") {
                val result = database.taskListDao().loadTaskListAndTasks()
                result.map {
                    it.first().tasks.count()
                }.test().await().assertValue(2)
            }
        }
    }
})