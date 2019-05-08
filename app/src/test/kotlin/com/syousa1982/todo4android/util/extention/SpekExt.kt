package com.syousa1982.todo4android.util.extention

import androidx.arch.core.executor.ArchTaskExecutor
import androidx.arch.core.executor.TaskExecutor
import org.spekframework.spek2.dsl.GroupBody

fun GroupBody.useLiveData() {
    beforeEachTest {
        ArchTaskExecutor.getInstance().setDelegate(InstantTaskExecutor)
    }
    afterEachTest {
        ArchTaskExecutor.getInstance().setDelegate(null)
    }
}

private object InstantTaskExecutor : TaskExecutor() {
    override fun executeOnDiskIO(runnable: Runnable) {
        runnable.run()
    }

    override fun isMainThread(): Boolean = true

    override fun postToMainThread(runnable: Runnable) {
        runnable.run()
    }
}