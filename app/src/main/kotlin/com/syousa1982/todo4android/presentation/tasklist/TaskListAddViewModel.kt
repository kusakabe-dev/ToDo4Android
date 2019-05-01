package com.syousa1982.todo4android.presentation.tasklist

import android.util.Log
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.domain.usecase.IToDoUseCase
import com.syousa1982.todo4android.presentation.BaseViewModel
import com.syousa1982.todo4android.util.extention.className
import com.syousa1982.todo4android.util.extention.default
import com.syousa1982.todo4android.util.extention.isFalse
import com.syousa1982.todo4android.util.extention.isNotEmpty
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy


/**
 * タスクリスト作成画面 ViewModel
 *
 * @param todoUseCase
 */
class TaskListAddViewModel(private val todoUseCase: IToDoUseCase) : BaseViewModel() {

    /**
     * 作成結果
     */
    val createResult = MutableLiveData<Result<Boolean>>()

    /**
     * プログレスバー表示フラグ
     */
    val isProgress = MutableLiveData<Boolean>().default(false)

    /**
     * タスクリスト名
     */
    val taskListName = MutableLiveData<String>()

    /**
     * 作成ボタンの有効判定
     */
    val buttonEnable = MediatorLiveData<Boolean>().default(false).apply {
        addSource(isProgress) { value = isButtonEnable() }
        addSource(taskListName) { value = isButtonEnable() }
    }

    override fun onStop() {
        super.onStop()
        createResult.value = null
    }

    fun create() {
        val name = taskListName.value ?: return
        todoUseCase.addTaskList(name).subscribeBy(
            onNext = { createResult.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }

    /**
     * ボタンの有効判定
     */
    private fun isButtonEnable() = isProgress.isFalse() && taskListName.isNotEmpty()
}