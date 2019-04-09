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
import io.reactivex.Single
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith


/**
 * ToDoUseCaseのテスト
 */
@RunWith(JUnitPlatform::class)
class ToDoUseCaseSpec : Spek({

    val taskListRepository: ITaskListRepository by lazy {
        mockk<ITaskListRepository>()
    }

    val schedulerProvider = TestSchedulerProvider()

    val todoUseCase: ToDoUseCase by lazy {
        ToDoUseCase(taskListRepository, schedulerProvider)
    }


    describe("Success Case") {
        context("ToDoUseCase#getTaskLists()") {
            every { taskListRepository.loadTaskListAndTasksByDB() } answers {
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

        context("ToDoUseCase#getTasks()") {
            every { taskListRepository.loadTaskListAndTasksByDB("1") } answers {
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
            val expectedValue = Result.success(listOf(
                Task(1, "aaaaa", Task.Status.DONE),
                Task(2, "aaaaa", Task.Status.TODO),
                Task(3, "aaaaa", Task.Status.TODO)
            ))
            todoUseCase.getTasks(1)
                .test()
                .assertValues(Result.Progress(), expectedValue)

        }
    }

//    describe("Failure Case") {
//        context("ToDoUseCase#getTaskLists()") {
//            every { taskListRepository.loadTaskListAndTasksByDB() } answers {
//                throw Exception()
//            }
//            todoUseCase
//                .getTaskLists()
//                .test()
//                .assertValues(Result.Progress(), Result.failure(Throwable()))
//        }
//    }

})