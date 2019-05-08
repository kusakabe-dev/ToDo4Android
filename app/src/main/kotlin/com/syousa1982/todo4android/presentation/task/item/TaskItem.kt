package com.syousa1982.todo4android.presentation.task.item

import com.bumptech.glide.Glide
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.ItemTaskBinding
import com.syousa1982.todo4android.domain.model.Task
import com.xwray.groupie.databinding.BindableItem

/**
 * タスク Item
 */
data class TaskItem(var task: Task? = null) : BindableItem<ItemTaskBinding>() {
    override fun getLayout(): Int = R.layout.item_task

    override fun bind(viewBinding: ItemTaskBinding, position: Int) {
        task?.let {
            viewBinding.taskName.text = it.name
            when (it.status) {
                Task.Status.TODO -> Glide.with(viewBinding.image).load(R.drawable.ic_check_off_24dp).into(viewBinding.image)
                Task.Status.DONE -> Glide.with(viewBinding.image).load(R.drawable.ic_check_on_24dp).into(viewBinding.image)
            }
        }
    }
}