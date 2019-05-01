package com.syousa1982.todo4android.presentation.tasklist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.domain.usecase.IToDoUseCase
import com.syousa1982.todo4android.presentation.BaseViewModel
import com.syousa1982.todo4android.util.extention.default
import com.syousa1982.todo4android.util.extention.isFalse
import com.syousa1982.todo4android.util.extention.isNotEmpty


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


    /**
     * ボタンの有効判定
     */
    private fun isButtonEnable() = isProgress.isFalse() && taskListName.isNotEmpty()
}