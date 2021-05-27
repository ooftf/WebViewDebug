package com.ooftf.webview.debug.original

//import android.webkit.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ooftf.webview.debug.Const
import com.ooftf.webview.debug.databinding.ActivityOriginalWebviewBinding

class OriginalWebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOriginalWebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOriginalWebviewBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        binding.webView.webChromeClient = OriginalWebChromeClient()
        binding.webView.webViewClient = OriginalWebViewClient()
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

    override fun onBackPressed() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        binding.webView.destroy()
        super.onDestroy()
    }


}