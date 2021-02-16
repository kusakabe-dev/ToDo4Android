package com.syousa1982.todo4android.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syousa1982.todo4android.data.db.entity.Task
import com.syousa1982.todo4android.databinding.FragmentTaskBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [TaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskFragment : Fragment() {

    lateinit var binding: FragmentTaskBinding

    private val viewModel : TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.testButton.setOnClickListener {
//            viewModel.add("テスト用")
//        }
        binding.navAddButton.setOnClickListener {

        }
        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            bindRecycler(it)
        })
    }

    private fun bindRecycler(tasks: List<Task>) {
        binding.taskList.adapter = GroupAdapter<GroupieViewHolder>()
        val linearLayoutManager = LinearLayoutManager(context)
        binding.taskList.layoutManager = linearLayoutManager

    }

    companion object {
        fun newInstance() = TaskFragment()
    }
}