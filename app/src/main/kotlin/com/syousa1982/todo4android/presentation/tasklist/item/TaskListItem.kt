package com.syousa1982.todo4android.presentation.tasklist.item

import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.ItemTaskListBinding
import com.syousa1982.todo4android.domain.model.TaskList
import com.xwray.groupie.databinding.BindableItem

/**
 * タスクリスト Item
 *
 * @param taskList
 */
data class TaskListItem(var taskList: TaskList? = null) : BindableItem<ItemTaskListBinding>() {

    override fun getLayout(): Int = R.layout.item_task_list

    override fun bind(viewBinding: ItemTaskListBinding, position: Int) {
        taskList?.let {
            viewBinding.taskListName.text = it.name
            viewBinding.taskCount.text = it.taskCount.toString()
        }
    }
}