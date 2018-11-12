package com.syousa1982.todo4android.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import com.ncapdevi.fragnav.FragNavTransactionOptions
import com.syousa1982.todo4android.R

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
     * Pop遷移時に設定するアニメーション用に履歴を保持する
     */
    private val transitionStack: MutableList<Transition> = mutableListOf()

    /**
     * 初期化する
     *
     * @param activity Activity
     * @param savedInstanceState Bundle
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
     * @param savedInstanceState Bundle
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
     * @param transition 遷移アニメーション定義
     */
    fun push(fragment: BaseFragment, transition: Transition = Transition.NONE) {
        val fragmentNavOptions = FragNavTransactionOptions.newBuilder().allowStateLoss(true)
        try {
            // 遷移アニメーション
            setCustomAnimations(transition, fragmentNavOptions)
            // アニメーション遷移
            transitionStack.add(transition)
            fragmentNavController.pushFragment(fragment, fragmentNavOptions.build())
        } catch (e: Exception) {
//            Log.e(className(), "Fragment遷移エラー : $e")
        }
    }

    /**
     * FragmentのPush遷移（前の画面にデータを結果を通知）
     * <p>
     * BaseFragmentのonFragmentResultで結果を受け取ります
     * </p>
     *
     * @param fragment BaseFragment
     * @param requestCode リクエストコード
     * @param transition 遷移アニメーション定義
     */
    fun push(fragment: BaseFragment, requestCode: RequestCode, transition: Transition = Transition.NONE) {
        fragment.setTargetFragment(current(), requestCode.ordinal)
        push(fragment, transition)
    }

    /**
     * FragmentのPop遷移
     *
     * @param popCount Popする数
     * @return Boolean 遷移したかどうか
     */
    fun pop(popCount: Int = 1): Boolean {
        if (fragmentNavController.isRootFragment) {
            return false
        }
        val fragmentNavOptions = FragNavTransactionOptions.newBuilder().allowStateLoss(true)
        return try {
            // 遷移アニメーション
            val stack = transitionStack.last()
            setCustomAnimations(stack, fragmentNavOptions)
            // アニメーション遷移
            transitionStack.dropLast(popCount)
            fragmentNavController.popFragments(popCount, fragmentNavOptions.build())
            true
        } catch (e: Exception) {
//            Log.e(className(), "Fragment遷移エラー : $e")
            false
        }
    }

    /**
     * FragmentのPop遷移（前の画面にデータを結果を通知）
     * <p>
     * BaseFragmentのonFragmentResultで結果を受け取ります
     * </p>
     *
     * @param resultCode 結果コード
     * @param data 結果データ
     * @return Boolean 遷移したかどうか
     */
    fun pop(resultCode: Int, data: Intent? = null): Boolean {
        val currentFragment = current()
        val targetFragment = currentFragment?.targetFragment as? BaseFragment
        if (currentFragment != null && targetFragment != null) {
            targetFragment.onFragmentResult(currentFragment.targetRequestCode, resultCode, data)
        }
        return pop(1)
    }

    /**
     * RootFragmentまでPop遷移
     */
    fun popRoot() {
        while (!fragmentNavController.isRootFragment) {
            fragmentNavController.popFragment()
        }
        transitionStack.clear()
    }


    /**
     * アニメーションを設定
     *
     * @param transition 遷移アニメーション定義
     * @param fragmentNavOptions 遷移オプション
     */
    private fun setCustomAnimations(transition: Transition, fragmentNavOptions: FragNavTransactionOptions.Builder) {
        when (transition) {
            Transition.PUSH -> {
                fragmentNavOptions.customAnimations(
                        R.anim.fragment_transition_slide_in_from_right,
                        R.anim.fragment_transition_fade_out,
                        R.anim.fragment_transition_fade_in,
                        R.anim.fragment_transition_slide_out_to_right
                )
            }
            Transition.MODAL -> {
                fragmentNavOptions.customAnimations(
                        R.anim.fragment_transition_slide_in_from_bottom,
                        R.anim.fragment_transition_fade_out,
                        R.anim.fragment_transition_fade_in,
                        R.anim.fragment_transition_slide_out_to_bottom
                )
            }
            Transition.FADE -> {
                fragmentNavOptions.customAnimations(
                        R.anim.fragment_transition_fade_in,
                        R.anim.fragment_transition_fade_out,
                        R.anim.fragment_transition_fade_in,
                        R.anim.fragment_transition_fade_out
                )
            }
            Transition.NONE -> {
                fragmentNavOptions.customAnimations(
                        0,
                        0,
                        0,
                        0
                )
            }
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

/**
 * 遷移アニメーション定義
 */
enum class Transition {

    /**
     * 右から左への遷移
     */
    PUSH,

    /**
     * 下から上への遷移
     */
    MODAL,

    /**
     * フェードイン/フェードアウトで遷移
     */
    FADE,

    /**
     * アニメーションなしで遷移
     */
    NONE;
}