package com.syousa1982.todo4android.view.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.syousa1982.todo4android.databinding.FragmentEditTaskBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class EditTaskFragment : BaseFragment() {

    private lateinit var binding: FragmentEditTaskBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentEditTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        fun newInstance(): EditTaskFragment = EditTaskFragment()
    }

}
