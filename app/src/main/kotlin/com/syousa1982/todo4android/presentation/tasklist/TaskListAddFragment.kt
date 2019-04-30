package com.syousa1982.todo4android.presentation.tasklist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todo4android.databinding.FragmentTaskListAddBinding
import com.syousa1982.todo4android.presentation.MainActivity


/**
 * A simple [Fragment] subclass.
 *
 */
class TaskListAddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTaskListAddBinding.inflate(inflater, container, false)
        bindOutput(binding)
        return binding.root
    }

    /**
     * 入力
     *
     * @param binding
     */
    private fun bindOutput(binding: FragmentTaskListAddBinding) {
        (requireActivity() as MainActivity).setAppBarTitle("タスクリスト作成")
    }


}
