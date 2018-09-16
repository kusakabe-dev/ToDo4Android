package com.syousa1982.todo4android.view.fragment


import android.app.Fragment
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syousa1982.todo4android.databinding.FragmentTaskBinding
import com.syousa1982.todo4android.databinding.FragmentTaskListBinding
import com.syousa1982.todo4android.extension.application
import com.syousa1982.todo4android.presenter.TaskPresenter
import com.syousa1982.todo4android.presenter.Viewable.TaskViewable
import com.syousa1982.todo4android.view.adapter.TaskRecyclerViewAdapter
import com.syousa1982.todo4android.viewmodel.fragment.TaskListViewModel
import com.syousa1982.todo4android.viewmodel.fragment.TaskViewModel


/**
 * タスク一覧 [Fragment] subclass.
 *
 */
class TaskFragment : BaseFragment(), TaskViewable, TaskRecyclerViewAdapter.OnItemListener {

    /**
     * バインディングインスタンス
     */
    private lateinit var binding: FragmentTaskBinding

    /**
     * Presenter
     */
    private lateinit var presenter: TaskPresenter

    /**
     * ViewModel
     */
    private lateinit var viewModel: TaskViewModel

    /**
     * Adapter
     */
    private val adapter = TaskRecyclerViewAdapter()

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = TaskPresenter(this, application().repository.task)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        binding.taskList.adapter = adapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(TaskViewModel::class.java)
        binding.viewModel = viewModel
        presenter.fetchTasks()
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }

    override fun onBindTasks(viewModels: List<TaskListViewModel>) {
        adapter.onItemListener = this
        adapter.setItems(viewModels)
    }

    override fun onFailureUpdateTask() {
        // Todo: リソース管理
        AlertDialog.Builder(requireContext())
                .setTitle("通信エラー")
                .setMessage("タスクの更新に失敗しました。")
                .setPositiveButton("閉じる") { _, _ ->
                    // データ不整合を防ぐため、タスク一覧を再度取得
                    presenter.fetchTasks()
                }
                .show()
    }

    override fun onClickTaskCheck(binding: FragmentTaskListBinding, checked: Boolean) {
        binding.viewModel?.recreateByParam(checked)?.let {
            presenter.updateTask(it)
        }
    }

    companion object {
        fun newInstance() : TaskFragment = TaskFragment()
    }
}
