package com.ooftf.webview.debug.original

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Message
import android.view.InputEvent
import android.view.KeyEvent
import android.webkit.*
import android.webkit.ClientCertRequest
import android.webkit.HttpAuthHandler
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import com.hunter.library.debug.HunterDebug

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/5/14
 */
class OriginalWebViewClient : WebViewClient() {
    @HunterDebug
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        return super.shouldOverrideUrlLoading(view, url)
    }
    @HunterDebug
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
    }
    @HunterDebug
    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
    }
    @HunterDebug
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
    }
    @HunterDebug
    override fun onLoadResource(view: WebView?, url: String?) {
        super.onLoadResource(view, url)
    }
    @HunterDebug
    override fun onPageCommitVisible(view: WebView?, url: String?) {
        super.onPageCommitVisible(view, url)
    }
    /*@HunterDebug
    override fun shouldInterceptRequest(view: WebView?, url: String?): WebResourceResponse {
        return super.shouldInterceptRequest(view, url)
    }
    @HunterDebug
    override fun shouldInterceptRequest(
        view: WebView?,
        request: WebResourceRequest?
    ): WebResourceResponse {
        return super.shouldInterceptRequest(view, request)
    }*/
    @HunterDebug
    override fun onTooManyRedirects(view: WebView?, cancelMsg: Message?, continueMsg: Message?) {
        super.onTooManyRedirects(view, cancelMsg, continueMsg)
    }
    @HunterDebug
    override fun onReceivedError(
        view: WebView?,
        errorCode: Int,
        description: String?,
        failingUrl: String?
    ) {
        super.onReceivedError(view, errorCode, description, failingUrl)
    }
    @HunterDebug
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
    }
    @HunterDebug
    override fun onReceivedHttpError(
        view: WebView?,
        request: WebResourceRequest?,
        errorResponse: WebResourceResponse?
    ) {
        super.onReceivedHttpError(view, request, errorResponse)
    }
    @HunterDebug
    override fun onFormResubmission(view: WebView?, dontResend: Message?, resend: Message?) {
        super.onFormResubmission(view, dontResend, resend)
    }
    @HunterDebug
    override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
        super.doUpdateVisitedHistory(view, url, isReload)
    }
    @HunterDebug
    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        super.onReceivedSslError(view, handler, error)
    }
    @HunterDebug
    override fun onReceivedClientCertRequest(view: WebView?, request: ClientCertRequest?) {
        super.onReceivedClientCertRequest(view, request)
    }
    @HunterDebug
    override fun onReceivedHttpAuthRequest(
        view: WebView?,
        handler: HttpAuthHandler?,
        host: String?,
        realm: String?
    ) {
        super.onReceivedHttpAuthRequest(view, handler, host, realm)
    }
    @HunterDebug
    override fun shouldOverrideKeyEvent(view: WebView?, event: KeyEvent?): Boolean {
        return super.shouldOverrideKeyEvent(view, event)
    }
    @HunterDebug
    override fun onUnhandledKeyEvent(view: WebView?, event: KeyEvent?) {
        super.onUnhandledKeyEvent(view, event)
    }
    @HunterDebug
    override fun onUnhandledInputEvent(view: WebView?, event: InputEvent?) {
        super.onUnhandledInputEvent(view, event)
    }
    @HunterDebug
    override fun onScaleChanged(view: WebView?, oldScale: Float, newScale: Float) {
        super.onScaleChanged(view, oldScale, newScale)
    }
    @HunterDebug
    override fun onReceivedLoginRequest(
        view: WebView?,
        realm: String?,
        account: String?,
        args: String?
    ) {
        super.onReceivedLoginRequest(view, realm, account, args)
    }
    @HunterDebug
    override fun onRenderProcessGone(view: WebView?, detail: RenderProcessGoneDetail?): Boolean {
        return super.onRenderProcessGone(view, detail)
    }
    @HunterDebug
    override fun onSafeBrowsingHit(
        view: WebView?,
        request: WebResourceRequest?,
        threatType: Int,
        callback: SafeBrowsingResponse?
    ) {
        super.onSafeBrowsingHit(view, request, threatType, callback)
    }
}