package com.syousa1982.todo4android.domain

import com.syousa1982.todo4android.data.repository.ITaskListRepository
import io.mockk.mockk
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class ToDoUseCaseSpec : Spek({

    val taskListRepository: ITaskListRepository by lazy {
        mockk<ITaskListRepository>()
    }

    describe("IToDoUseCase#getTaskLists") {}
    describe("IToDoUseCase#getTasks") {}
})