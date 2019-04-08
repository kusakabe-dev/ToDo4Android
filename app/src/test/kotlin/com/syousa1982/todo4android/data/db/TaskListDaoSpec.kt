package com.syousa1982.todo4android.data.db

import android.content.Context
import androidx.room.Room
import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import io.mockk.mockk
import io.reactivex.Single
import org.dbtools.android.room.jdbc.JdbcSQLiteOpenHelperFactory
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.given
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
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
        val result: Single<List<TaskListAndTasks>> by lazy {
            database.taskListDao().loadTaskListAndTasks()
        }
        context("Todoリストにタスク2件追加") {
            val taskList = TaskListEntity(1, "Todoリスト")
            val tasks = listOf(
                    TaskEntity(1, 1, "hoge", "done"),
                    TaskEntity(2, 1, "piyo", "todo")
            )
            database.taskListDao().insertTaskLists(taskList).test().await().assertComplete()
            tasks.forEach {
                database.taskListDao().insertTasks(it).test().await().assertComplete()
            }
            given("タスクリスト1件存在しているか") {
                result.map {
                    it.count()
                }.test().await().assertValue(1)
            }
            given("タスク2件存在しているか") {
                result.map {
                    it.first().tasks.count()
                }.test().await().assertValue(2)
            }
        }
    }
})