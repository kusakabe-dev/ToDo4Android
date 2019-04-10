package com.syousa1982.todo4android.domain

import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import com.syousa1982.todo4android.data.repository.ITaskListRepository
import com.syousa1982.todo4android.domain.model.Task
import com.syousa1982.todo4android.domain.model.TaskList
import com.syousa1982.todo4android.domain.usecase.ToDoUseCase
import com.syousa1982.todo4android.util.rx.TestSchedulerProvider
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith


/**
 * ToDoUseCaseのテスト
 */
@RunWith(JUnitPlatform::class)
class ToDoUseCaseSpec : Spek({
    describe("ToDoUseCase") {
        lateinit var taskListRepository: ITaskListRepository
        lateinit var todoUseCase: ToDoUseCase
        /**
         * 正常系テスト
         */
        context("Success") {
            beforeEachTest {
                taskListRepository = mockk<ITaskListRepository>().also {
                    every { it.loadTaskListAndTasksByDB() } answers {
                        val taskListAndTasks = TaskListAndTasks()
                        taskListAndTasks.taskList = TaskListEntity(1, "todo")
                        taskListAndTasks.tasks = listOf(
                            TaskEntity(1, 1, "aaaaa", Task.Status.DONE.value.toLowerCase()),
                            TaskEntity(2, 1, "aaaaa", Task.Status.TODO.value.toLowerCase()),
                            TaskEntity(3, 1, "aaaaa", Task.Status.TODO.value.toLowerCase())
                        )
                        Single.fromCallable {
                            listOf(taskListAndTasks)
                        }
                    }
                    every { it.loadTaskListAndTasksByDB("1") } answers {
                        val taskListAndTasks = TaskListAndTasks()
                        taskListAndTasks.taskList = TaskListEntity(1, "todo")
                        taskListAndTasks.tasks = listOf(
                            TaskEntity(1, 1, "aaaaa", Task.Status.DONE.value.toLowerCase()),
                            TaskEntity(2, 1, "aaaaa", Task.Status.TODO.value.toLowerCase()),
                            TaskEntity(3, 1, "aaaaa", Task.Status.TODO.value.toLowerCase())
                        )
                        Single.fromCallable {
                            taskListAndTasks
                        }
                    }
                }
                todoUseCase = ToDoUseCase(taskListRepository, TestSchedulerProvider())
            }

            it("getTaskLists()") {
                val expectedValue =
                    Result.success(listOf(
                        TaskList(1, "todo", listOf(
                            Task(1, "aaaaa", Task.Status.DONE),
                            Task(2, "aaaaa", Task.Status.TODO),
                            Task(3, "aaaaa", Task.Status.TODO)
                        ))
                    ))
                todoUseCase
                    .getTaskLists()
                    .test()
                    .assertValues(Result.Progress(), expectedValue)
            }

            it("getTasks()") {
                val expectedValue = Result.success(listOf(
                    Task(1, "aaaaa", Task.Status.DONE),
                    Task(2, "aaaaa", Task.Status.TODO),
                    Task(3, "aaaaa", Task.Status.TODO)
                ))
                todoUseCase.getTasks(1)
                    .test()
                    .assertValues(Result.Progress(), expectedValue)
            }

            afterEachTest {
                unmockkAll()
            }
        }

        /**
         * 異常系テスト
         */
        context("Failure Test") {
            // todo 異常系のテストを記述
        }
    }
})