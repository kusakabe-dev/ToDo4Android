package com.syousa1982.todo4android.view.fragment


import android.app.Activity.RESULT_OK
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
import com.syousa1982.todo4android.databinding.FragmentAddTaskBinding
import com.syousa1982.todo4android.extension.*
import com.syousa1982.todo4android.presenter.AddTaskPresenter
import com.syousa1982.todo4android.presenter.Viewable.AddTaskViewable
import com.syousa1982.todo4android.viewmodel.fragment.AddTaskViewModel


/**
 * タスク追加Fragment
 *
 */
class AddTaskFragment : BaseFragment(), AddTaskViewable, View.OnClickListener {

    /**
     * バインディングインスタンス
     */
    private lateinit var binding: FragmentAddTaskBinding

    /**
     * Presenter
     */
    private lateinit var presenter: AddTaskPresenter

    /**
     * viewModel
     */
    private lateinit var viewModel: AddTaskViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter = AddTaskPresenter(this, application().repository.task)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        binding.addButton.setOnClickListener(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity()).get(AddTaskViewModel::class.java)
        binding.viewModel = viewModel
    }

    override fun onDestroy() {
        presenter.onDestroy()
        hideKeyboard()
        super.onDestroy()
    }

    override fun showSendProgress() {
        binding.progressBar.toVisible()
        viewModel.observer.inProgress = true
    }

    override fun dismissSendProgress() {
        binding.progressBar.toGone()
        viewModel.observer.inProgress = false
    }

    override fun onSuccessAddTask() {
        // Todo: リソース管理
        AlertDialog.Builder(requireContext())
                .setTitle("成功")
                .setMessage("タスクの追加に成功しました。")
                .setPositiveButton("閉じる") { _, _ ->
                    viewModel.clear()
                    val data = Intent()
                    pop(RESULT_OK, data)
                }
                .show()
    }

    override fun onFailureAddTask() {
        // Todo: リソース管理
        AlertDialog.Builder(requireContext())
                .setTitle("通信エラー")
                .setMessage("タスクの追加に失敗しました。")
                .setPositiveButton("閉じる", null)
                .show()
    }

    override fun onClick(v: View?) {
        v?.pauseClickTimer()
        when (v?.id) {
            R.id.add_button -> {
                val task = viewModel.create()
                presenter.addTask(task)
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

        /**
         * インスタンス生成
         *
         * @return EditTaskFragment
         */
        fun newInstance(): AddTaskFragment = AddTaskFragment()
    }

}
