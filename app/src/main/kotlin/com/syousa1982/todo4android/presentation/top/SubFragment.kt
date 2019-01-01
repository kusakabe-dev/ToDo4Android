package com.syousa1982.todo4android.presentation.top


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.syousa1982.todo4android.databinding.FragmentSubBinding

/**
 * A simple [Fragment] subclass.
 *
 */
class SubFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSubBinding.inflate(inflater, container, false)
        bindInput(binding)
        return binding.root
    }

    private fun bindInput(binding: FragmentSubBinding) {
        binding.toBackButton.setOnClickListener {
            fragmentManager?.let {
                it.popBackStack()
            }
        }
    }

    companion object {
        fun newInstance() = SubFragment()
    }


}
