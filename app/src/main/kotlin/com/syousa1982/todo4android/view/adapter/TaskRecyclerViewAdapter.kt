package com.syousa1982.todo4android.view.adapter

import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.syousa1982.todo4android.databinding.FragmentTaskListBinding
import com.syousa1982.todo4android.extension.pauseClickTimer
import com.syousa1982.todo4android.model.constant.ViewType
import com.syousa1982.todo4android.viewmodel.fragment.TaskListViewModel

/**
 * タスクリスト Adapter
 */
class TaskRecyclerViewAdapter : BaseRecyclerViewAdapter<TaskListViewModel>() {

    /**
     * アイテムリスナー
     */
    var onItemListener: OnItemListener? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        val linearLayoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.layoutManager = linearLayoutManager
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, linearLayoutManager.orientation)
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.isNestedScrollingEnabled = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when (viewType) {
            ViewType.VIEW_TYPE_1.ordinal -> {
                TaskViewHolder(FragmentTaskListBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> {
                throw IllegalStateException("不正なViewTypeです : $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = items[position]
        when(holder.itemViewType) {
            ViewType.VIEW_TYPE_1.ordinal -> {
                (holder as TaskViewHolder).bind(item)
            }
        }
    }

    private inner class TaskViewHolder(val binding: FragmentTaskListBinding) : BaseViewHolder(binding) {

        fun bind(item: TaskListViewModel) {
            binding.viewModel = item
            binding.taskCheck.setOnClickListener {
                it.pauseClickTimer()
                val check = binding.taskCheck.isChecked
                onItemListener?.onClickTaskCheck(binding, check)
            }
            binding.taskName.setOnClickListener {
                it.pauseClickTimer()
                onItemListener?.onClickTaskName(binding)
            }
        }
    }

    /**
     * アイテムリスナー
     */
    interface OnItemListener {

        /**
         * チェックボックスをチェック
         *
         * @param binding FragmentTaskListBinding
         */
        fun onClickTaskCheck(binding: FragmentTaskListBinding, checked:Boolean)

        /**
         * タスク名をクリック
         */
        fun onClickTaskName(binding: FragmentTaskListBinding)
    }
}