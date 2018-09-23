package com.syousa1982.todo4android.view.activity

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.ActivityMainBinding
import com.syousa1982.todo4android.extension.clear
import com.syousa1982.todo4android.extension.pop
import com.syousa1982.todo4android.view.fragment.AddTaskFragment
import com.syousa1982.todo4android.view.fragment.BaseFragment
import com.syousa1982.todo4android.view.fragment.EditTaskFragment
import com.syousa1982.todo4android.view.fragment.TaskFragment

class MainActivity : BaseActivity() {

    /**
     * メイン画面 バインディング インスタンス
     */
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
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

    override fun showScreenProgress() {
        super.showScreenProgress()
        binding.loading = true
    }

    override fun dismissScreenProgress() {
        super.dismissScreenProgress()
        binding.loading = false
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
                binding.toolbar.title = "タスク一覧"
            }
            is AddTaskFragment -> {
                binding.toolbar.title = "タスク追加"
                binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
                binding.toolbar.setNavigationOnClickListener { pop() }
            }
            is EditTaskFragment -> {
                binding.toolbar.title = "タスク編集"
                binding.toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
                binding.toolbar.setNavigationOnClickListener { pop() }
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
