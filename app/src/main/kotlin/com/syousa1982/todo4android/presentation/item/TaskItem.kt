package com.syousa1982.todo4android.presentation.item

import com.bumptech.glide.Glide
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.data.db.entity.Task
import com.syousa1982.todo4android.data.db.entity.TaskStatus
import com.syousa1982.todo4android.databinding.ItemTaskBinding
import com.xwray.groupie.databinding.BindableItem

class TaskItem(private val task: Task) : BindableItem<ItemTaskBinding>() {
    override fun getLayout(): Int = R.layout.item_task

    override fun bind(viewBinding: ItemTaskBinding, position: Int) {
        viewBinding.taskName.text = task.name
        when (task.status) {
            TaskStatus.TODO.value -> Glide.with(viewBinding.image).load(R.drawable.ic_check_off_24dp).into(viewBinding.image)
            TaskStatus.DONE.value -> Glide.with(viewBinding.image).load(R.drawable.ic_check_on_24dp).into(viewBinding.image)
        }
    }
}