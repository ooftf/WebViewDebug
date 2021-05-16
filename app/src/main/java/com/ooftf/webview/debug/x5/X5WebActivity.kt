package com.ooftf.webview.debug.x5

//import android.webkit.*
import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ooftf.webview.debug.Const
import com.ooftf.webview.debug.databinding.ActivityX5WebviewBinding
import com.tbruyelle.rxpermissions3.RxPermissions
import com.tencent.smtt.export.external.extension.proxy.ProxyWebChromeClientExtension

class X5WebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityX5WebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityX5WebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        binding.webView.webChromeClient = X5WebChromeClient()
        binding.webView.webViewClient = X5WebViewClient()
        binding.webView.webChromeClientExtension = ProxyWebChromeClientExtension()

        binding.webView.loadUrl(Const.getCurrentUrl())

    }

    override fun onPause() {
        binding.webView.onPause()
        super.onPause()
    }

    override fun onResume() {
        binding.webView.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        binding.webView.destroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}