package com.syousa1982.todo4android.view.fragment


import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.FragmentEditTaskBinding
import com.syousa1982.todo4android.extension.*
import com.syousa1982.todo4android.model.entity.Task
import com.syousa1982.todo4android.presenter.EditTaskPresenter
import com.syousa1982.todo4android.presenter.Viewable.EditTaskViewable
import com.syousa1982.todo4android.viewmodel.fragment.EditTaskViewModel


/**
 * タスク編集 Fragment
 *
 */
class EditTaskFragment : BaseFragment(), EditTaskViewable, View.OnClickListener {

    /**
     * バインディングインスタンス
     */
    private lateinit var binding: FragmentEditTaskBinding

    /**
     * Presenter
     */
    private lateinit var presenter: EditTaskPresenter

    /**
     * ViewModel
     */
    private lateinit var viewModel: EditTaskViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = EditTaskPresenter(this, application().repository.task)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        binding.editButton.setOnClickListener(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(EditTaskViewModel::class.java)
        binding.viewModel = viewModel
        arguments?.let {
            val id = it.getString(BUNDLE_TASK_ID)
            presenter.fetchTask(id)
        }
    }

    override fun onStop() {
        hideKeyboard()
        super.onStop()
    }

    override fun onDestroyView() {
        presenter.onDestroy()
        super.onDestroyView()
    }

    override fun showProgress() {
        showScreenProgress()
    }

    override fun dismissProgress() {
        dismissScreenProgress()
    }

    override fun showSendProgress() {
        binding.progressBar.toVisible()
        viewModel.observer.inProgress = true
    }

    override fun dismissSendProgress() {
        binding.progressBar.toGone()
        viewModel.observer.inProgress = false
    }

    override fun onBindTask(task: Task) {
        viewModel.setTask(task)
    }

    override fun onSuccessUpdateTask() {
        // Todo: リソース管理
        AlertDialog.Builder(requireContext())
                .setTitle("成功")
                .setMessage("タスクの更新に成功しました。")
                .setPositiveButton("閉じる") { _, _ ->
                    viewModel.clear()
                    val data = Intent()
                    pop(Activity.RESULT_OK, data)
                }
                .show()
    }

    override fun onFailureUpdateTask() {
        // Todo: リソース管理
        AlertDialog.Builder(requireContext())
                .setTitle("通信エラー")
                .setMessage("タスクの更新に失敗しました。")
                .setPositiveButton("閉じる", null)
                .show()
    }

    override fun onClick(v: View?) {
        v?.pauseClickTimer()
        when (v?.id) {
            R.id.edit_button -> {
                viewModel.recreate()?.let {
                    presenter.updateTask(it)
                }
            }
        }
    }

    /**
     * 画面遷移時にソフトキーボードを非表示
     */
    private fun hideKeyboard() {
        activity?.currentFocus?.let {
            val manager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    companion object {

        private val BUNDLE_TASK_ID = "com.syousa1982.todo4android.view.fragment.EditTaskFragment.BUNDLE_TASK_ID"

        /**
         * インスタンス生成
         *
         * @return EditTaskFragment
         */
        fun newInstance(id: String): EditTaskFragment = EditTaskFragment().apply {
            arguments = Bundle().apply {
                putString(BUNDLE_TASK_ID, id)
            }
        }
    }

}
