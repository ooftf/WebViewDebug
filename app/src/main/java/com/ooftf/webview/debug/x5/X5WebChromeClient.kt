package com.ooftf.webview.debug.x5

import android.graphics.Bitmap
import android.net.Uri
import android.os.Message
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.hunter.library.debug.HunterDebug
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import com.luck.picture.lib.tools.PictureFileUtils
import com.ooftf.basic.utils.getFragmentActivity
import com.ooftf.webview.debug.GlideEngine
import com.tencent.smtt.export.external.interfaces.*
import com.tencent.smtt.sdk.ValueCallback
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebStorage
import com.tencent.smtt.sdk.WebView
import java.io.File


/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/5/14
 */
class X5WebChromeClient : WebChromeClient() {
    @HunterDebug
    override fun onExceededDatabaseQuota(
        p0: String?,
        p1: String?,
        p2: Long,
        p3: Long,
        p4: Long,
        p5: WebStorage.QuotaUpdater?
    ) {
        super.onExceededDatabaseQuota(p0, p1, p2, p3, p4, p5)
    }

    @HunterDebug
    override fun getDefaultVideoPoster(): Bitmap {
        return super.getDefaultVideoPoster()
    }

    @HunterDebug
    override fun getVisitedHistory(p0: ValueCallback<Array<String>>?) {
        super.getVisitedHistory(p0)
    }

    @HunterDebug
    override fun onConsoleMessage(p0: ConsoleMessage?): Boolean {
        return super.onConsoleMessage(p0)
    }

    @HunterDebug
    override fun onCreateWindow(p0: WebView?, p1: Boolean, p2: Boolean, p3: Message?): Boolean {
        return super.onCreateWindow(p0, p1, p2, p3)
    }

    @HunterDebug
    override fun onGeolocationPermissionsHidePrompt() {
        super.onGeolocationPermissionsHidePrompt()
    }

    @HunterDebug
    override fun onGeolocationPermissionsShowPrompt(
        p0: String?,
        p1: GeolocationPermissionsCallback?
    ) {
        super.onGeolocationPermissionsShowPrompt(p0, p1)
    }

    @HunterDebug
    override fun onHideCustomView() {
        super.onHideCustomView()
    }

    @HunterDebug
    override fun onJsAlert(p0: WebView?, p1: String?, p2: String?, p3: JsResult?): Boolean {
        return super.onJsAlert(p0, p1, p2, p3)
    }

    @HunterDebug
    override fun onJsConfirm(p0: WebView?, p1: String?, p2: String?, p3: JsResult?): Boolean {
        return super.onJsConfirm(p0, p1, p2, p3)
    }

    @HunterDebug
    override fun onJsPrompt(
        p0: WebView?,
        p1: String?,
        p2: String?,
        p3: String?,
        p4: JsPromptResult?
    ): Boolean {
        return super.onJsPrompt(p0, p1, p2, p3, p4)
    }

    @HunterDebug
    override fun onJsBeforeUnload(p0: WebView?, p1: String?, p2: String?, p3: JsResult?): Boolean {
        return super.onJsBeforeUnload(p0, p1, p2, p3)
    }

    @HunterDebug
    override fun onJsTimeout(): Boolean {
        return super.onJsTimeout()
    }

    @HunterDebug
    override fun onProgressChanged(p0: WebView?, p1: Int) {
        super.onProgressChanged(p0, p1)
    }

    @HunterDebug
    override fun onReachedMaxAppCacheSize(p0: Long, p1: Long, p2: WebStorage.QuotaUpdater?) {
        super.onReachedMaxAppCacheSize(p0, p1, p2)
    }

    @HunterDebug
    override fun onReceivedIcon(p0: WebView?, p1: Bitmap?) {
        super.onReceivedIcon(p0, p1)
    }

    @HunterDebug
    override fun onReceivedTouchIconUrl(p0: WebView?, p1: String?, p2: Boolean) {
        super.onReceivedTouchIconUrl(p0, p1, p2)
    }

    @HunterDebug
    override fun onReceivedTitle(p0: WebView?, p1: String?) {
        super.onReceivedTitle(p0, p1)
    }

    @HunterDebug
    override fun onRequestFocus(p0: WebView?) {
        super.onRequestFocus(p0)
    }

    @HunterDebug
    override fun onShowCustomView(p0: View?, p1: IX5WebChromeClient.CustomViewCallback?) {
        super.onShowCustomView(p0, p1)
    }

    @HunterDebug
    override fun onShowCustomView(p0: View?, p1: Int, p2: IX5WebChromeClient.CustomViewCallback?) {
        super.onShowCustomView(p0, p1, p2)
    }

    @HunterDebug
    override fun onCloseWindow(p0: WebView?) {
        super.onCloseWindow(p0)
    }

    @HunterDebug
    override fun getVideoLoadingProgressView(): View {
        return super.getVideoLoadingProgressView()
    }

    @HunterDebug
    override fun openFileChooser(p0: ValueCallback<Uri>?, p1: String?, p2: String?) {
        super.openFileChooser(p0, p1, p2)
    }
    //  To cancel the request, call filePathCallback.onReceiveValue(null) and return true.
    // 返回 true 代表取消请求
    @HunterDebug
    override fun onShowFileChooser(
        webView: WebView,
        valueCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        fileChooserParams?.acceptTypes?.firstOrNull()?.let {
            webView.context.getFragmentActivity()?.let { fragmentActivity ->
                PictureSelector.create(webView.context.getFragmentActivity())
                    .openGallery(PictureMimeType.getMimeType(it))
                    .imageEngine(GlideEngine.createGlideEngine())
                    .forResult(object : OnResultCallbackListener<LocalMedia> {
                        override fun onResult(result: List<LocalMedia>) {
                            valueCallback.onReceiveValue(result.map {
                                PictureFileUtils.parUri(fragmentActivity, File(it.path) )
                            }.toTypedArray())
                        }

                        override fun onCancel() {

                            valueCallback.onReceiveValue(null)
                        }
                    })
                return true
            }
        }
        valueCallback.onReceiveValue(null)
        return true
    }

    @HunterDebug
    override fun onPermissionRequest(p0: PermissionRequest?) {
        super.onPermissionRequest(p0)
    }

    @HunterDebug
    override fun onPermissionRequestCanceled(p0: PermissionRequest?) {
        super.onPermissionRequestCanceled(p0)
    }
}