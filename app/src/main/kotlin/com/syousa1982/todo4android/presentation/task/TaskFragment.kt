package com.syousa1982.todo4android.presentation.task


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todo4android.databinding.FragmentTaskBinding
import com.syousa1982.todo4android.presentation.MainActivity
import com.syousa1982.todo4android.util.extention.className
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
        Log.d(className(), "taskListid: ${taskList.id}")
        (requireActivity() as MainActivity).setAppBarTitle(taskList.name)
        return binding.root
    }

}
