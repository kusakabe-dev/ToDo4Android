package com.syousa1982.todo4android.presentation

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {


    /**
     * Fragmentの遷移を通知するインタフェース
     */
    interface TransitionFragmentListener {

        /**
         * Fragmentの通常遷移を通知
         *
         * @param fragment BaseFragment
         */
        fun onFragmentTransaction(fragment: BaseFragment)
    }
}