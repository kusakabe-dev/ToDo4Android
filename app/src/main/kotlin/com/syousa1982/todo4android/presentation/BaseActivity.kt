package com.syousa1982.todo4android.presentation

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), FragmentController.RootFragmentListener, FragmentController.TransitionFragmentListener {

    /**
     * Fragment管理クラス
     */
    val fragmentController: FragmentController = FragmentController()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            fragmentController.onCreate(this, savedInstanceState)
        }
    }
}