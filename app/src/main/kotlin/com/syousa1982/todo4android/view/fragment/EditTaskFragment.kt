package com.syousa1982.todo4android.view.fragment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syousa1982.todo4android.databinding.FragmentEditTaskBinding


/**
 * タスク編集 Fragment
 *
 */
class EditTaskFragment : BaseFragment() {

    private lateinit var binding: FragmentEditTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        return binding.root
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
