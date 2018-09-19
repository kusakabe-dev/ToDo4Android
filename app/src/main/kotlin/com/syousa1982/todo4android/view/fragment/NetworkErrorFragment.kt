package com.syousa1982.todo4android.view.fragment


import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.FragmentNetworkErrorBinding
import com.syousa1982.todo4android.extension.className
import com.syousa1982.todo4android.extension.finishActivity
import com.syousa1982.todo4android.extension.pauseClickTimer
import com.syousa1982.todo4android.helper.NetworkHelper


/**
 * ネットワークエラー画面 Fragment
 */
class NetworkErrorFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentNetworkErrorBinding.inflate(inflater, container, false)
        binding.reloadNetworkButton.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View?) {
        v?.let {
            it.pauseClickTimer()
            when (it.id) {
                R.id.reload_network_button -> checkNetwork()
            }
        }
    }

    /**
     * ネットワーク更新
     */
    private fun checkNetwork() {
        val activity = activity ?: return
        if (!NetworkHelper.isConnect(activity)) {
            Log.e(className(), "ネットワークが確認できません")
            Toast.makeText(activity, "ネットワークが確認できません", Toast.LENGTH_SHORT).show()
            return
        }
        // ネットワークが確認できたので、エラー画面を閉じる
        finishActivity(Activity.RESULT_OK)
    }

    companion object {

        /**
         * インスタンスを生成
         *
         * @return NetworkErrorFragment
         */
        fun newInstance(): NetworkErrorFragment {
            return NetworkErrorFragment()
        }
    }

}
