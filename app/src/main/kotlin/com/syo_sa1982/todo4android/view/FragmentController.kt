package com.syo_sa1982.todo4android.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.syo_sa1982.todo4android.R
import com.syo_sa1982.todo4android.extension.className
import com.syo_sa1982.todo4android.view.fragment.BaseFragment

class FragmentController : FragNavController.TransactionListener, FragNavController.RootFragmentListener {

    /**
     * Fragmentの管理を行うクラス
     */
    private lateinit var fragmentNavController: FragNavController

    /**
     * Fragmentの生成を行うインタフェース
     */
    private lateinit var rootFragmentListener: RootFragmentListener

    /**
     * Fragmentの遷移を通知するインタフェース
     */
    private lateinit var transitionFragmentListener: TransitionFragmentListener

    /**
     * 初期化する
     *
     * @param activity AppCompatActivity
     * @param savedInstanceState Bundle?
     */
    fun onCreate(activity: AppCompatActivity, savedInstanceState: Bundle?) {
        if (activity is TransitionFragmentListener && activity is RootFragmentListener) {
            transitionFragmentListener = activity
            rootFragmentListener = activity
        } else {
            throw IllegalStateException("TransitionFragmentListener / RootFragmentListener を Activityに設定してください")
        }
        fragmentNavController = FragNavController
                .newBuilder(savedInstanceState, activity.supportFragmentManager, R.id.container)
                .rootFragmentListener(this, rootFragmentListener.getRootFragmentCount())
                .transactionListener(this)
                .build()
    }

    /**
     * Bundleを保存する
     *
     * @param savedInstanceState Bundle?
     */
    fun onSaveInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            fragmentNavController.onSaveInstanceState(savedInstanceState)
        }
    }

    override fun getRootFragment(index: Int): Fragment {
        // ルートFragmentを生成する
        return rootFragmentListener.onCreateRootFragment(index)
    }

    override fun onFragmentTransaction(fragment: Fragment?, type: FragNavController.TransactionType?) {
        // Fragmentの遷移処理が行われた際にコールされます
        if (fragment is BaseFragment) {
            transitionFragmentListener.onFragmentTransaction(fragment)
        }
    }

    override fun onTabTransaction(fragment: Fragment?, index: Int) {
        // Fragmentのタブが切り替えられた場合にコールされます
        if (fragment is BaseFragment) {
            transitionFragmentListener.onFragmentTabTransaction(fragment, index)
        }
    }

    /**
     * 現在のFragmentを取得
     *
     * @return BaseFragment
     */
    fun current(): BaseFragment? {
        return fragmentNavController.currentFrag as? BaseFragment
    }


    /**
     * FragmentのPush遷移
     *
     * @param fragment BaseFragment
     */
    fun push(fragment: BaseFragment) {
        val fragmentNavOptions = FragNavTransactionOptions.newBuilder().allowStateLoss(true)
        try {
            fragmentNavController.pushFragment(fragment, fragmentNavOptions.build())
        } catch (e: Exception) {
            Log.e(className(), "Fragment遷移エラー : $e")
        }
    }

    /**
     * FragmentのPop遷移
     *
     * @param popCount Popする数
     * @return Boolean 遷移したかどうか
     */
    fun pop(popCount: Int = 1):Boolean{
        if (fragmentNavController.isRootFragment) {
            return false
        }
        val fragmentNavOptions = FragNavTransactionOptions.newBuilder().allowStateLoss(true)
        return try {
            fragmentNavController.popFragments(popCount, fragmentNavOptions.build())
            true
        } catch (e: Exception){
            Log.e(className(), "Fragment遷移エラー : $e")
            false
        }
    }

    /**
     * Fragmentの生成を行うインタフェース
     */
    interface RootFragmentListener {

        /**
         * RootFragment数を取得
         *
         * @return インデックス
         */
        fun getRootFragmentCount(): Int

        /**
         * RootFragmentを生成
         *
         * @param index インデックス
         * @return BaseFragment
         */
        fun onCreateRootFragment(index: Int): BaseFragment
    }

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

        /**
         * Fragmentのタブ遷移を通知
         *
         * @param fragment BaseFragment
         */
        fun onFragmentTabTransaction(fragment: BaseFragment, index: Int)
    }

}
