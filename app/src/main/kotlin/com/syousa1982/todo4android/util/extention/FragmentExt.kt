package com.syousa1982.todo4android.util.extention

import androidx.fragment.app.Fragment
import com.syousa1982.todo4android.presentation.BaseActivity
import com.syousa1982.todo4android.presentation.BaseFragment

fun Fragment.baseActivity(): BaseActivity? = (activity as? BaseActivity)

fun Fragment.push(fragment: BaseFragment) = baseActivity()?.push(fragment)

fun Fragment.pop() = baseActivity()?.pop()