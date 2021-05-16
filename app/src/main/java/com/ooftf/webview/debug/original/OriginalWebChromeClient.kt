package com.ooftf.webview.debug.original

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Message
import android.view.View
import android.webkit.*
import com.hunter.library.debug.HunterDebug
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.ooftf.basic.utils.getFragmentActivity
import com.ooftf.webview.debug.GlideEngine


/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/5/14
 */
class OriginalWebChromeClient : WebChromeClient() {
    @HunterDebug
    override fun onProgressChanged(view: WebView?, newProgress: Int) {
        super.onProgressChanged(view, newProgress)
    }

    @HunterDebug
    override fun onReceivedTitle(view: WebView?, title: String?) {
        super.onReceivedTitle(view, title)
    }

    @HunterDebug
    override fun onReceivedIcon(view: WebView?, icon: Bitmap?) {
        super.onReceivedIcon(view, icon)
    }

    @HunterDebug
    override fun onReceivedTouchIconUrl(view: WebView?, url: String?, precomposed: Boolean) {
        super.onReceivedTouchIconUrl(view, url, precomposed)
    }

    @HunterDebug
    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        super.onShowCustomView(view, callback)
    }

    @HunterDebug
    override fun onShowCustomView(
        view: View?,
        requestedOrientation: Int,
        callback: CustomViewCallback?
    ) {
        super.onShowCustomView(view, requestedOrientation, callback)
    }

    @HunterDebug
    override fun onHideCustomView() {
        super.onHideCustomView()
    }

    @HunterDebug
    override fun onCreateWindow(
        view: WebView?,
        isDialog: Boolean,
        isUserGesture: Boolean,
        resultMsg: Message?
    ): Boolean {
        return super.onCreateWindow(view, isDialog, isUserGesture, resultMsg)
    }

    @HunterDebug
    override fun onRequestFocus(view: WebView?) {
        super.onRequestFocus(view)
    }

    @HunterDebug
    override fun onCloseWindow(window: WebView?) {
        super.onCloseWindow(window)
    }

    @HunterDebug
    override fun onJsAlert(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsAlert(view, url, message, result)
    }

    @HunterDebug
    override fun onJsConfirm(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsConfirm(view, url, message, result)
    }

    @HunterDebug
    override fun onJsPrompt(
        view: WebView?,
        url: String?,
        message: String?,
        defaultValue: String?,
        result: JsPromptResult?
    ): Boolean {
        return super.onJsPrompt(view, url, message, defaultValue, result)
    }

    @HunterDebug
    override fun onJsBeforeUnload(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {
        return super.onJsBeforeUnload(view, url, message, result)
    }

    @HunterDebug
    override fun onExceededDatabaseQuota(
        url: String?,
        databaseIdentifier: String?,
        quota: Long,
        estimatedDatabaseSize: Long,
        totalQuota: Long,
        quotaUpdater: WebStorage.QuotaUpdater?
    ) {
        super.onExceededDatabaseQuota(
            url,
            databaseIdentifier,
            quota,
            estimatedDatabaseSize,
            totalQuota,
            quotaUpdater
        )
    }

    @HunterDebug
    override fun onReachedMaxAppCacheSize(
        requiredStorage: Long,
        quota: Long,
        quotaUpdater: WebStorage.QuotaUpdater?
    ) {
        super.onReachedMaxAppCacheSize(requiredStorage, quota, quotaUpdater)
    }

    @HunterDebug
    override fun onGeolocationPermissionsShowPrompt(
        origin: String?,
        callback: GeolocationPermissions.Callback?
    ) {
        super.onGeolocationPermissionsShowPrompt(origin, callback)
    }

    @HunterDebug
    override fun onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt()
    }

    @HunterDebug
    override fun onPermissionRequest(request: PermissionRequest?) {
        super.onPermissionRequest(request)
    }

    @HunterDebug
    override fun onPermissionRequestCanceled(request: PermissionRequest?) {
        super.onPermissionRequestCanceled(request)
    }

    @HunterDebug
    override fun onJsTimeout(): Boolean {
        return super.onJsTimeout()
    }

    @HunterDebug
    override fun onConsoleMessage(message: String?, lineNumber: Int, sourceID: String?) {
        super.onConsoleMessage(message, lineNumber, sourceID)
    }

    @HunterDebug
    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        return super.onConsoleMessage(consoleMessage)
    }

    /*@HunterDebug
    override fun getDefaultVideoPoster(): Bitmap {
        return super.getDefaultVideoPoster()
    }*/

    @HunterDebug
    override fun getVideoLoadingProgressView(): View {
        return super.getVideoLoadingProgressView()
    }

    @HunterDebug
    override fun getVisitedHistory(callback: ValueCallback<Array<String>>?) {
        super.getVisitedHistory(callback)
    }

    @HunterDebug
    override fun onShowFileChooser(
        webView: WebView,
        valueCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            fileChooserParams?.acceptTypes?.firstOrNull()?.let {
                webView.context.getFragmentActivity()?.let { fragmentActivity ->
                    PictureSelector.create(webView.context.getFragmentActivity())
                        .openGallery(PictureMimeType.getMimeType(it))
                        .imageEngine(GlideEngine.createGlideEngine())
                        .forResult(object : OnResultCallbackListener<LocalMedia> {
                            override fun onResult(result: List<LocalMedia>) {
                                valueCallback.onReceiveValue(result.map {
                                    Uri.parse(it.path)
                                }.toTypedArray())
                            }

                            override fun onCancel() {
                                valueCallback.onReceiveValue(null)
                            }
                        })
                    return true
                }
            }
        }
        valueCallback.onReceiveValue(null)
        return false
    }

    @HunterDebug
    override fun openFileChooser(
        uploadFile: ValueCallback<Uri>?,
        acceptType: String?,
        capture: String?
    ) {
        super.openFileChooser(uploadFile, acceptType, capture)
    }
}