package com.ooftf.webview.debug.original

//import android.webkit.*
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.ooftf.webview.debug.Const
import com.ooftf.webview.debug.databinding.ActivityOriginalWebviewBinding

class OriginalWebActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOriginalWebviewBinding
    val client by lazy {
        OriginalWebChromeClient()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOriginalWebviewBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.webView.settings.apply {
            javaScriptEnabled = true
            domStorageEnabled = true
        }
        binding.webView.webChromeClient = client
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //client.onActivityResult(requestCode, resultCode, data)
    }

}