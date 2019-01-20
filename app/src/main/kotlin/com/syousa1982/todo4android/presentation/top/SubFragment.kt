package com.syousa1982.todo4android.presentation.top


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.syousa1982.todo4android.databinding.FragmentSubBinding
import com.syousa1982.todo4android.presentation.BaseFragment

/**
 * A simple [Fragment] subclass.
 *
 */
class SubFragment : BaseFragment() {

    lateinit var transitionFragmentListener: TransitionFragmentListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        transitionFragmentListener = requireActivity() as TransitionFragmentListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentSubBinding.inflate(inflater, container, false)
        transitionFragmentListener.onFragmentTransaction(this)
        bindInput(binding)
        return binding.root
    }

    private fun bindInput(binding: FragmentSubBinding) {
        binding.toBackButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

    companion object {
        fun newInstance() = SubFragment()
    }


}
