package com.syousa1982.todo4android.presentation.top


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.FragmentTopBinding
import com.syousa1982.todo4android.util.extention.setOnClickPauseListener

/**
 * A simple [Fragment] subclass.
 *
 */
class TopFragment : Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentTopBinding.inflate(inflater, container, false)
        bindInput(binding)
        return binding.root
    }


    private fun bindInput(binding: FragmentTopBinding) {
        binding.toSubButton.setOnClickPauseListener {
            Navigation.findNavController(it).navigate(R.id.action_top_to_sub)
        }
    }

    companion object {

        fun newInstance() = TopFragment()
    }
}
