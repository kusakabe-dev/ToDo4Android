package com.syousa1982.todo4android.presentation.task


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.syousa1982.todo4android.databinding.FragmentTaskAddBinding
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.presentation.MainActivity
import com.syousa1982.todo4android.util.extention.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * タスク作成 [Fragment] subclass.
 *
 */
class TaskAddFragment : Fragment() {

    private val viewModel: TaskAddViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTaskAddBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).setAppBarTitle("タスク作成")
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
    private fun bindInputViewModel(binding: FragmentTaskAddBinding, viewModel: TaskAddViewModel) {
        binding.inputTaskName.setOnChangedTextListener {
            viewModel.taskName.value = it
        }
        binding.createTaskButton.setOnClickPauseListener {
            val taskListId = TaskAddFragmentArgs.fromBundle(arguments ?: return@setOnClickPauseListener).taskListId
            viewModel.create(taskListId)
        }
    }

    /**
     * 出力
     *
     * @param binding
     * @param viewModel
     */
    private fun bindOutputViewModel(binding: FragmentTaskAddBinding, viewModel: TaskAddViewModel) {
        viewModel.buttonEnable.observe(this) {
            binding.createTaskButton.isEnabled = it
        }
        viewModel.isProgress.observe(this) {
            binding.progressBar.visibleOrInvisible(it)
        }
        viewModel.createResult.observe(this) {
            when (it) {
                is Result.Progress -> {
                    Log.d(className(), "タスク作成開始")
                    viewModel.isProgress.value = true
                }
                is Result.Success -> {
                    Log.d(className(), "タスク作成成功")
                    viewModel.isProgress.value = false
                    Navigation.findNavController(binding.root).popBackStack()
                }
                is Result.Failure -> {
                    Log.d(className(), "タスク作成失敗", it.e)
                    viewModel.isProgress.value = false
                }
            }
        }
    }


}
