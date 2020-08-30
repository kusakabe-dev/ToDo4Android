package com.syousa1982.todo4android.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.FragmentTaskBinding
import kotlinx.android.synthetic.main.fragment_task.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [TaskFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskFragment : Fragment() {

    lateinit var binding: FragmentTaskBinding

    val viewModel : TaskViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.tasks.observe(this, Observer {
            test_text.text = it.toString()
        })
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
        binding.testButton.setOnClickListener {
            viewModel.add("テスト用")
        }
    }

    companion object {
        fun newInstance() = TaskFragment()
    }
}