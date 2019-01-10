package com.syousa1982.todo4android.util.extention

import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.presentation.BaseActivity
import com.syousa1982.todo4android.presentation.BaseFragment

fun BaseActivity.push(fragment: BaseFragment, targetId: Int = R.id.container) {
    val fragmentTransaction = supportFragmentManager.beginTransaction()
    fragmentTransaction.replace(targetId, fragment)
    fragmentTransaction.addToBackStack(null)
    fragmentTransaction.commit()
}

fun BaseActivity.pop() {
    supportFragmentManager.popBackStack()
}