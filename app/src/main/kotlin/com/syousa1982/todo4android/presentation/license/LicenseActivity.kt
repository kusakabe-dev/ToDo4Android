package com.syousa1982.todo4android.presentation.license

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.syousa1982.todo4android.R
import com.syousa1982.todo4android.databinding.ActivityLicenseBinding


class LicenseActivity : AppCompatActivity() {


    /**
     * ライセンス画面 バインディング
     */
    private val binding by lazy {
        DataBindingUtil.setContentView<ActivityLicenseBinding>(this, R.layout.activity_license)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val webView = binding.webView
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.loadUrl(LICENSE_FILE)
    }

    companion object {

        /**
         * ライセンスファイル
         */
        private const val LICENSE_FILE = "file:///android_asset/licenses.html"
    }
}
