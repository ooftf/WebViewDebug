package com.ooftf.webview.debug.x5

import android.graphics.Bitmap
import android.os.Bundle
import android.os.Message
import android.view.KeyEvent
import com.hunter.library.debug.HunterDebug
import com.tencent.smtt.export.external.interfaces.*
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/5/14
 */
class X5WebViewClient : WebViewClient() {
    @HunterDebug
    override fun onLoadResource(p0: WebView?, p1: String?) {
        super.onLoadResource(p0, p1)
    }
    @HunterDebug
    override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
        return super.shouldOverrideUrlLoading(p0, p1)
    }
    @HunterDebug
    override fun shouldOverrideUrlLoading(p0: WebView?, p1: WebResourceRequest?): Boolean {
        return super.shouldOverrideUrlLoading(p0, p1)
    }
    @HunterDebug
    override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
        super.onPageStarted(p0, p1, p2)
    }
    @HunterDebug
    override fun onPageFinished(p0: WebView?, p1: String?) {
        super.onPageFinished(p0, p1)
    }
    @HunterDebug
    override fun onReceivedError(p0: WebView?, p1: Int, p2: String?, p3: String?) {
        super.onReceivedError(p0, p1, p2, p3)
    }
    @HunterDebug
    override fun onReceivedError(p0: WebView?, p1: WebResourceRequest?, p2: WebResourceError?) {
        super.onReceivedError(p0, p1, p2)
    }
    @HunterDebug
    override fun onReceivedHttpError(
        p0: WebView?,
        p1: WebResourceRequest?,
        p2: WebResourceResponse?
    ) {
        super.onReceivedHttpError(p0, p1, p2)
    }
   /* @HunterDebug
    override fun shouldInterceptRequest(p0: WebView?, p1: String?): WebResourceResponse {
        return super.shouldInterceptRequest(p0, p1)
    }*/
    /*@HunterDebug
    override fun shouldInterceptRequest(
        p0: WebView?,
        p1: WebResourceRequest?
    ): WebResourceResponse {
        return super.shouldInterceptRequest(p0, p1)
    }*/
    /*@HunterDebug
    override fun shouldInterceptRequest(
        p0: WebView?,
        p1: WebResourceRequest?,
        p2: Bundle?
    ): WebResourceResponse {
        return super.shouldInterceptRequest(p0, p1, p2)
    }*/
    @HunterDebug
    override fun doUpdateVisitedHistory(p0: WebView?, p1: String?, p2: Boolean) {
        super.doUpdateVisitedHistory(p0, p1, p2)
    }
    @HunterDebug
    override fun onFormResubmission(p0: WebView?, p1: Message?, p2: Message?) {
        super.onFormResubmission(p0, p1, p2)
    }
    @HunterDebug
    override fun onReceivedHttpAuthRequest(
        p0: WebView?,
        p1: HttpAuthHandler?,
        p2: String?,
        p3: String?
    ) {
        super.onReceivedHttpAuthRequest(p0, p1, p2, p3)
    }
    @HunterDebug
    override fun onReceivedSslError(p0: WebView?, p1: SslErrorHandler?, p2: SslError?) {
        super.onReceivedSslError(p0, p1, p2)
    }
    @HunterDebug
    override fun onReceivedClientCertRequest(p0: WebView?, p1: ClientCertRequest?) {
        super.onReceivedClientCertRequest(p0, p1)
    }
    @HunterDebug
    override fun onScaleChanged(p0: WebView?, p1: Float, p2: Float) {
        super.onScaleChanged(p0, p1, p2)
    }
    @HunterDebug
    override fun onUnhandledKeyEvent(p0: WebView?, p1: KeyEvent?) {
        super.onUnhandledKeyEvent(p0, p1)
    }
    @HunterDebug
    override fun shouldOverrideKeyEvent(p0: WebView?, p1: KeyEvent?): Boolean {
        return super.shouldOverrideKeyEvent(p0, p1)
    }
    @HunterDebug
    override fun onTooManyRedirects(p0: WebView?, p1: Message?, p2: Message?) {
        super.onTooManyRedirects(p0, p1, p2)
    }
    @HunterDebug
    override fun onReceivedLoginRequest(p0: WebView?, p1: String?, p2: String?, p3: String?) {
        super.onReceivedLoginRequest(p0, p1, p2, p3)
    }
    @HunterDebug
    override fun onDetectedBlankScreen(p0: String?, p1: Int) {
        super.onDetectedBlankScreen(p0, p1)
    }
    @HunterDebug
    override fun onPageCommitVisible(p0: WebView?, p1: String?) {
        super.onPageCommitVisible(p0, p1)
    }
    @HunterDebug
    override fun onRenderProcessGone(p0: WebView?, p1: RenderProcessGoneDetail?): Boolean {
        return super.onRenderProcessGone(p0, p1)
    }
}