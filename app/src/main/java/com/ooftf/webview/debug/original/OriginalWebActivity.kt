package com.ooftf.webview.debug.original

//import android.webkit.*
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.webView.loadUrl(Const.URL)

    }


}