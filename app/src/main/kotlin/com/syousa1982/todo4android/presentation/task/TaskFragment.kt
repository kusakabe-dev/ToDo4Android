package com.syousa1982.todo4android.presentation.task


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
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
        bindOutputView(binding, viewModel)
        bindRecyclerView(binding, viewModel)
        return binding.root
    }

    private fun bindInputView(binding: FragmentTaskBinding, viewModel: TaskViewModel) {
        binding.addButton.setOnClickPauseListener {
            Log.d(className(), "bindInputView#taskListid: ${viewModel.taskListId.value}")
            Navigation.findNavController(it).navigate(
                TaskFragmentDirections.actionTaskFragmentToTaskAddFragment().apply {
                    taskListId = viewModel.taskListId.value ?: throw IllegalAccessException("タスクリストIDが指定されていません")
                }
            )
        }
    }

    private fun bindOutputView(binding: FragmentTaskBinding, viewModel: TaskViewModel) {
        viewModel.updateResult.observe(this) {
            when (it) {
                is Result.Progress -> {
                    Log.d(className(), "タスク更新開始")
                }
                is Result.Success -> {
                    Log.d(className(), "タスク更新完了")
                    viewModel.getTasks()
                    viewModel.updateResult.value = null
                }
                is Result.Failure -> {
                    val actionName = "タスク更新"
                    Log.d(className(), "$actionName 失敗", it.e)
                    showErrorMessage(actionName)
                }
            }
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
            val task = item.task ?: return@setGroupieOnItemClickListener
            viewModel.updateTask(task)
        }
        binding.tasks.setGroupieOnItemLongClickListener<TaskItem> { item, view ->
            showDeleteDialog(item)
            return@setGroupieOnItemLongClickListener true
        }
        // Output
        viewModel.tasks.observe(this) {
            when (it) {
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
                    val actionName = "タスク取得"
                    Log.d(className(), "$actionName 失敗", it.e)
                    showErrorMessage(actionName)
                }
            }
        }
    }


    private fun showDeleteDialog(item: TaskItem) {
        AlertDialog.Builder(requireContext())
            .setTitle("確認")
            .setMessage("タスクを削除しますか？")
            .setPositiveButton("削除する") { _, _ ->
                item.task?.let { task ->
                    viewModel.delete(task)
                }
            }
            .setNegativeButton("キャンセル", null)
            .show()
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
