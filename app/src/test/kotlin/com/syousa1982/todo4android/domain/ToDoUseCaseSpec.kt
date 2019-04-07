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

    every { taskListRepository.loadTaskListAndTasksByDB() } answers {
        val taskListAndTasks = TaskListAndTasks()
        taskListAndTasks.taskList = TaskListEntity(1, "todo")
        taskListAndTasks.tasks = listOf(
                TaskEntity(1, 1, "aaaaa", Task.Status.DONE.value.toLowerCase()),
                TaskEntity(2, 1, "aaaaa", Task.Status.TODO.value.toLowerCase()),
                TaskEntity(3, 1, "aaaaa", Task.Status.TODO.value.toLowerCase())
        )
        Single.create {
            listOf(taskListAndTasks)
        }
    }


    describe("ToDoUseCase") {
        val expectedValue = Result.success(listOf(
                TaskList(1, "todo", listOf(
                        Task(1, "aaaaa", Task.Status.DONE),
                        Task(2, "aaaaa", Task.Status.TODO),
                        Task(3, "aaaaa", Task.Status.TODO)
                ))
        ))
        todoUseCase
                .getTaskLists()
                .test()
                .assertValue(expectedValue)
    }

//    describe("IToDoUseCase#getTasks") {
//        val result = listOf(
//                Task(1, "aaaaa", Task.Status.DONE),
//                Task(2, "aaaaa", Task.Status.TODO),
//                Task(3, "aaaaa", Task.Status.TODO)
//        )
//        todoUseCase.getTasks(1).test().assertOf {
//            it.assertComplete().assertResult(
//                    Result.success(result)
//            )
//            it.assertError(Throwable()).assertResult(
//                    Result.failure(Throwable())
//            )
//        }
//    }

})