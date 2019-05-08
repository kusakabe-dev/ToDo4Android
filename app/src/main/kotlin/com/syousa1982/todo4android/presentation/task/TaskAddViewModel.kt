package com.syousa1982.todo4android.presentation.task

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
 * タスク追加画面 ViewModel
 */
class TaskAddViewModel(private val todoUseCase: IToDoUseCase) : BaseViewModel() {

    /**
     * 作成結果
     */
    val createResult = MutableLiveData<Result<Boolean>>()

    /**
     * プログレスバー表示フラグ
     */
    val isProgress = MutableLiveData<Boolean>().default(false)

    /**
     * タスク名
     */
    val taskName = MutableLiveData<String>()


    /**
     * 作成ボタンの有効判定
     */
    val buttonEnable = MediatorLiveData<Boolean>().default(false).apply {
        addSource(isProgress) { value = isButtonEnable() }
        addSource(taskName) { value = isButtonEnable() }
    }

    override fun onStop() {
        super.onStop()
        createResult.value = null
    }

    /**
     * タスク作成
     *
     * @param taskListId
     */
    fun create(taskListId: Int) {
        val name = taskName.value ?: return
        todoUseCase.addTask(taskListId, name).subscribeBy(
            onNext = { createResult.value = it },
            onError = { e -> Log.e(className(), "エラー発生", e) }
        ).addTo(disposable)
    }

    /**
     * ボタンの有効判定
     */
    private fun isButtonEnable() = isProgress.isFalse() && taskName.isNotEmpty()
}