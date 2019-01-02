package com.syousa1982.todo4android.presentation

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), BaseFragment.TransitionFragmentListener {

    override fun onFragmentTransaction(fragment: BaseFragment) {
        onCreateToolbar(fragment)
    }

    open fun onCreateToolbar(fragment: BaseFragment) = Unit
}