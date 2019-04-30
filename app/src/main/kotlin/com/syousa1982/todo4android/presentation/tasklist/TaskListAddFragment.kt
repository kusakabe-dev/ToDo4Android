package com.syousa1982.todo4android.presentation.tasklist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todo4android.R


/**
 * A simple [Fragment] subclass.
 *
 */
class TaskListAddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_list_add, container, false)
    }


}
