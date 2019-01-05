package com.syousa1982.todo4android.presentation

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.ActivityMainBinding
import com.syousa1982.todo4android.presentation.top.SubFragment
import com.syousa1982.todo4android.presentation.top.TopFragment
import com.syousa1982.todo4android.util.extention.clear

class MainActivity : BaseActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(binding.container.id, TopFragment.newInstance())
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onCreateToolbar(fragment: BaseFragment) {
        super.onCreateToolbar(fragment)
        binding.toolbar.clear()
        when (fragment) {
            is TopFragment -> {
                binding.toolbar.title = "Top"
            }
            is SubFragment -> {
                binding.toolbar.title = "Sub"
                binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
                binding.toolbar.setNavigationOnClickListener {
                    fragment.fragmentManager?.popBackStack()
                }
            }
        }
    }
}
