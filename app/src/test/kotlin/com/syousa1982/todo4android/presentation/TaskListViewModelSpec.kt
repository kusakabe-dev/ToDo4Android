package com.syousa1982.todo4android.presentation

import androidx.lifecycle.Observer
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.domain.model.Task
import com.syousa1982.todo4android.domain.model.TaskList
import com.syousa1982.todo4android.domain.usecase.IToDoUseCase
import com.syousa1982.todo4android.presentation.tasklist.TaskListViewModel
import com.syousa1982.todo4android.util.extention.useLiveData
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.reactivex.Flowable
import org.junit.Assert.assertEquals
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class TaskListViewModelSpec : Spek({
    useLiveData()
    describe("TaskListViewModel") {
        lateinit var observer: Observer<Result<List<TaskList>>>
        lateinit var todoUseCase: IToDoUseCase
        lateinit var viewModel: TaskListViewModel
        beforeEachTest {
            observer = mockk<Observer<Result<List<TaskList>>>>().also {
                every { it.onChanged(any()) } just Runs
            }
            todoUseCase = mockk<IToDoUseCase>().also {
                every { it.getTaskLists() } answers {
                    Flowable.fromCallable {
                        Result.success(listOf(
                            TaskList(1, "todo", listOf(
                                Task(1, "aaaaa", Task.Status.DONE),
                                Task(2, "aaaaa", Task.Status.TODO),
                                Task(3, "aaaaa", Task.Status.TODO)
                            )),
                            TaskList(2, "家事", listOf(
                                Task(1, "飯作る", Task.Status.DONE),
                                Task(2, "皿洗う", Task.Status.DONE),
                                Task(3, "掃除", Task.Status.TODO)
                            ))
                        ))
                    }
                }
            }
            viewModel = TaskListViewModel(todoUseCase)
        }
        it("fetchTasks") {
            viewModel.taskLists.observeForever(observer)
            run {
                viewModel.fetchTasks()
            }
            val expectValue = Result.success(listOf(
                TaskList(1, "todo", listOf(
                    Task(1, "aaaaa", Task.Status.DONE),
                    Task(2, "aaaaa", Task.Status.TODO),
                    Task(3, "aaaaa", Task.Status.TODO)
                )),
                TaskList(2, "家事", listOf(
                    Task(1, "飯作る", Task.Status.DONE),
                    Task(2, "皿洗う", Task.Status.DONE),
                    Task(3, "掃除", Task.Status.TODO)
                ))
            ))
            assertEquals(expectValue, viewModel.taskLists.value)
        }
    }
})