package com.syousa1982.todo4android.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.ActivityMainBinding
import com.syousa1982.todo4android.extension.clear
import com.syousa1982.todo4android.view.fragment.BaseFragment
import com.syousa1982.todo4android.view.fragment.TaskFragment

class MainActivity : BaseActivity() {

    /**
     * メイン画面 バインディング インスタンス
     */
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateRootFragment(index: Int): BaseFragment {
        return TaskFragment.newInstance()
    }

    override fun onFragmentTransaction(fragment: BaseFragment) {
        super.onFragmentTransaction(fragment)
        setToolbarFromFragment(fragment)
    }

    override fun onFragmentTabTransaction(fragment: BaseFragment, index: Int) {
        super.onFragmentTabTransaction(fragment, index)
        setToolbarFromFragment(fragment)
    }

    /**
     * FragmentからToolbarを設定
     *
     * @param fragment BaseFragment
     */
    private fun setToolbarFromFragment(fragment: BaseFragment) {
        binding.toolbar.clear()
        when (fragment) {
            is TaskFragment -> {
                binding.toolbar.title = "Todo list"
            }
        }
    }


    companion object {

        /**
         * Intentを生成
         *
         * @param context Context
         * @return Intent
         */
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
