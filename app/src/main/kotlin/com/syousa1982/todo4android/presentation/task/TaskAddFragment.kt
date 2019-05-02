package com.syousa1982.todo4android.presentation.task


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todo4android.databinding.FragmentTaskAddBinding
import com.syousa1982.todo4android.presentation.MainActivity

/**
 * タスク作成 [Fragment] subclass.
 *
 */
class TaskAddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTaskAddBinding.inflate(inflater, container, false)
        (requireActivity() as MainActivity).setAppBarTitle("タスク作成")
        return binding.root
    }


}
