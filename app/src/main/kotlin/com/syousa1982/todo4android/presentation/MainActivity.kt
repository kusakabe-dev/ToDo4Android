package com.syousa1982.todo4android.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.ActivityMainBinding
import com.syousa1982.todo4android.presentation.top.TopFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragmentManager = supportFragmentManager
        val frammentTransaction = fragmentManager.beginTransaction()
        frammentTransaction.replace(binding.container.id, TopFragment.newInstance())
        frammentTransaction.addToBackStack(null)
        frammentTransaction.commit()
    }
}
