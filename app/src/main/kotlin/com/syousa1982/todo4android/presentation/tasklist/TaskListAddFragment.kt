package com.syousa1982.todo4android.presentation.tasklist


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.syousa1982.todo4android.databinding.FragmentTaskListAddBinding
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.presentation.MainActivity
import com.syousa1982.todo4android.util.extention.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * タスクリスト作成 [Fragment] subclass.
 *
 */
class TaskListAddFragment : Fragment() {

    private val viewModel: TaskListAddViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTaskListAddBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        bindInputViewModel(binding, viewModel)
        bindOutputViewModel(binding, viewModel)
        return binding.root
    }

    /**
     * 入力
     *
     * @param binding
     * @param viewModel
     */
    private fun bindInputViewModel(binding: FragmentTaskListAddBinding, viewModel: TaskListAddViewModel) {
        binding.inputTaskListName.setOnChangedTextListener {
            viewModel.taskListName.value = it
        }
        binding.createTaskListButton.setOnClickPauseListener {
            viewModel.create()
        }
    }

    /**
     * 出力
     *
     * @param binding
     * @param viewModel
     */
    private fun bindOutputViewModel(binding: FragmentTaskListAddBinding, viewModel: TaskListAddViewModel) {
        viewModel.buttonEnable.observe(this) {
            binding.createTaskListButton.isEnabled = it
        }
        viewModel.isProgress.observe(this) {
            binding.progressBar.visibleOrInvisible(it)
        }
        viewModel.createResult.observe(this) {
            when (it) {
                is Result.Progress -> {
                    Log.d(className(), "タスクリスト作成開始")
                    viewModel.isProgress.value = true
                }
                is Result.Success -> {
                    Log.d(className(), "タスクリスト作成成功")
                    viewModel.isProgress.value = false
                    Navigation.findNavController(binding.root).popBackStack()
                }
                is Result.Failure -> {
                    val actionName = "タスクリスト作成"
                    Log.d(className(), "$actionName 失敗", it.e)
                    viewModel.isProgress.value = false
                    showErrorMessage(actionName)
                }
            }
        }
    }

    /**
     * エラーメッセージ表示
     */
    private fun showErrorMessage(actionName: String) {
        view?.let {
            Snackbar.make(it, "$actionName 失敗しました。", Snackbar.LENGTH_LONG).show()
        }
    }
}
