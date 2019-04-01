package com.syousa1982.todo4android.domain

import com.syousa1982.todo4android.data.db.entity.TaskEntity
import com.syousa1982.todo4android.data.db.entity.TaskListAndTasks
import com.syousa1982.todo4android.data.db.entity.TaskListEntity
import com.syousa1982.todo4android.data.repository.ITaskListRepository
import com.syousa1982.todo4android.domain.model.Task
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Single
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class ToDoUseCaseSpec : Spek({

    val taskListRepository: ITaskListRepository by lazy {
        mockk<ITaskListRepository>()
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

    describe("IToDoUseCase#getTaskLists") {

    }
    describe("IToDoUseCase#getTasks") {}
})