package com.syousa1982.todo4android.presentation.task


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todo4android.databinding.FragmentTaskBinding
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.presentation.MainActivity
import com.syousa1982.todo4android.presentation.task.item.TaskItem
import com.syousa1982.todo4android.util.extention.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


/**
 * タスク一覧 [Fragment] subclass.
 *
 */
class TaskFragment : Fragment() {

    private val viewModel: TaskViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTaskBinding.inflate(inflater, container, false)
        val taskList = TaskFragmentArgs.fromBundle(arguments ?: return view).taskList
        lifecycle.addObserver(viewModel)
        Log.d(className(), "onCreateView#taskListid: ${taskList.id}")
        viewModel.taskListId.value = taskList.id
        (requireActivity() as MainActivity).setAppBarTitle(taskList.name)
        bindInputView(binding, viewModel)
        bindRecyclerView(binding, viewModel)
        return binding.root
    }

    private fun bindInputView(binding: FragmentTaskBinding, viewModel: TaskViewModel) {
        binding.addButton.setOnClickPauseListener {
            // todo:タスク追加画面へ遷移
            Log.d(className(), "bindInputView#taskListid: ${viewModel.taskListId.value}")
        }
    }

    /**
     * タスク一覧を出力
     *
     * @param binding
     * @param viewModel
     */
    private fun bindRecyclerView(binding: FragmentTaskBinding, viewModel: TaskViewModel) {
        // Input
        binding.tasks.setGroupieAdapter()
        binding.tasks.setLinearLayoutManagerWithDivider()
        binding.tasks.setGroupieOnItemClickListener<TaskItem> { item, view ->
            // todo:タスクの状態更新
        }
        // Output
        viewModel.tasks.observe(this) {
            when(it) {
                is Result.Progress -> {
                    Log.d(className(), "タスク取得開始")
                }
                is Result.Success -> {
                    Log.d(className(), "タスク取得成功")
                    val items = it.data.map { task ->
                        TaskItem(task)
                    }
                    binding.tasks.getGroupieAdapter().update(items)
                }
                is Result.Failure -> {
                    Log.d(className(), "タスク取得失敗", it.e)
                }
            }
        }
    }

}
