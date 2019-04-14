package com.syousa1982.todo4android.presentation

import androidx.lifecycle.Observer
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.domain.model.Task
import com.syousa1982.todo4android.domain.model.TaskList
import com.syousa1982.todo4android.presentation.tasklist.TaskListViewModel
import com.syousa1982.todo4android.util.extention.useLiveData
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(JUnitPlatform::class)
class TaskListViewModelSpec : Spek({
    describe("TaskListViewModel") {
        useLiveData()
        lateinit var viewModel: TaskListViewModel
        context("Success") {
            beforeEachTest {
                viewModel = TaskListViewModel()
            }
            it("fetchTasks") {
                val observer = mockk<Observer<Result<List<TaskList>>>> {
                    every { onChanged(any()) } just Runs
                }
                viewModel.taskLists.observeForever(observer)
                run {
                    viewModel.fetchTasks()
                }
                val expectValue = Result.success(
                    listOf(
                        TaskList(1, "ToDo", listOf(
                            Task(1, "ほげ", Task.Status.DONE),
                            Task(2, "会社を爆破", Task.Status.TODO),
                            Task(3, "殺意駆動開発", Task.Status.TODO)
                        )),
                        TaskList(2, "家事", listOf(
                            Task(1, "飯作る", Task.Status.DONE),
                            Task(2, "皿洗う", Task.Status.DONE),
                            Task(3, "掃除", Task.Status.TODO)
                        ))
                    )
                )
                assertEquals(expectValue, viewModel.taskLists.value)
            }
        }
    }
})