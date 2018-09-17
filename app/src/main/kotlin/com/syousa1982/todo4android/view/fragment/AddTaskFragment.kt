package com.syousa1982.todo4android.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syousa1982.todo4android.databinding.FragmentAddTaskBinding


/**
 * タスク追加Fragment
 *
 */
class AddTaskFragment : BaseFragment() {

    private lateinit var binding: FragmentAddTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance(): AddTaskFragment = AddTaskFragment()
    }

}
