package com.syousa1982.todo4android.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.view.fragment.BaseFragment
import com.syousa1982.todo4android.view.fragment.NetworkErrorFragment

class NetworkErrorActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_error)
    }

    override fun onCreateRootFragment(index: Int): BaseFragment {
        return NetworkErrorFragment.newInstance()
    }

    override fun onBackPressed() {
        // バックボタンを許容しない
        super.moveTaskToBack(true)
    }

    companion object {

        /**
         * Intentを生成
         *
         * @param context Context
         * @return Intent
         */
        fun newIntent(context: Context): Intent {
            return Intent(context, NetworkErrorActivity::class.java)
        }
    }
}
