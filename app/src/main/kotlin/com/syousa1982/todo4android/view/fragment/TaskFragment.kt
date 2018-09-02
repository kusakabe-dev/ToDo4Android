package com.syousa1982.todo4android.view.fragment


import android.os.Bundle
import android.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.FragmentTaskBinding


/**
 * タスク一覧 [Fragment] subclass.
 *
 */
class TaskFragment : BaseFragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        fun newInstance() : TaskFragment = TaskFragment()
    }
}
