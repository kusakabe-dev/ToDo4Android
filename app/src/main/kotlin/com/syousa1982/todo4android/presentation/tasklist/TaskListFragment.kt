package com.syousa1982.todo4android.presentation.tasklist


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todo4android.databinding.FragmentTaskListBinding
import com.syousa1982.todo4android.domain.Result
import com.syousa1982.todo4android.presentation.tasklist.item.TaskListItem
import com.syousa1982.todo4android.util.extention.*
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * タスクリスト一覧 [Fragment] subclass.
 *
 */
class TaskListFragment : Fragment() {

    private val viewModel: TaskListViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTaskListBinding.inflate(inflater, container, false)
        lifecycle.addObserver(viewModel)
        bindRecyclerView(binding, viewModel)
        return binding.root
    }


    private fun bindRecyclerView(binding: FragmentTaskListBinding, viewModel: TaskListViewModel) {
        // Input
        binding.taskList.setGroupieAdapter()
        binding.taskList.setLinearLayoutManagerWithDivider()
        binding.taskList.setGroupieOnItemClickListener<TaskListItem> { item, _ ->
            item.taskList?.let {
                // todo: TaskFragmentに遷移
            }
        }
        // Output
        viewModel.taskLists.observe(this) {
            when (it) {
                is Result.Progress -> {
                    Log.d(className(), "タスクリスト取得開始")
                }
                is Result.Success -> {
                    Log.d(className(), "タスクリスト取得成功")
                    val items = it.data.map { taskList ->
                        TaskListItem(taskList)
                    }
                    binding.taskList.getGroupieAdapter().update(items)
                }
                is Result.Failure -> {
                    Log.d(className(), "タスクリスト取得失敗", it.e)
                }
            }
        }
    }

}
