package com.syousa1982.todo4android.presentation.tasklist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todo4android.R
import org.koin.android.viewmodel.ext.android.sharedViewModel

/**
 * タスクリスト一覧 [Fragment] subclass.
 *
 */
class TaskListFragment : Fragment() {

    private val viewModel: TaskListViewModel by sharedViewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tasklist, container, false)
    }


}
