package com.ooftf.webview.debug.original

import android.app.Activity
import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.hardware.camera2.CameraCharacteristics
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Message
import android.provider.MediaStore
import android.view.View
import android.webkit.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.core.os.EnvironmentCompat
import com.hunter.library.debug.HunterDebug
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/5/14
 */
class OriginalWebChromeClient2(val activity:Activity) : WebChromeClient(),ActivityResultCallback {
    //用于保存拍照图片的uri
    private var mCameraUri: Uri? = null
    private var mUri: ValueCallback<Array<Uri>>? = null
    private var videoFlag: Boolean = false
    // 用于保存图片的文件路径，Android 10以下使用图片路径访问图片
    private var mCameraImagePath = ""

    // 是否是Android 10以上手机
    private val isAndroidQ = Build.VERSION.SDK_INT >= 29
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


    //  To cancel the request, call filePathCallback.onReceiveValue(null) and return true.
    // 返回 true 代表取消请求
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    @HunterDebug
    override fun onShowFileChooser(
        webView: WebView,
        filePathCallback: ValueCallback<Array<Uri>>,
        fileChooserParams: FileChooserParams?
    ): Boolean {
        /* 调用相册 */
        /* 如果不为空  重新置空 */
        mUri?.onReceiveValue(null)
        mUri = filePathCallback
        videoFlag = false
        val i = Intent(Intent.ACTION_GET_CONTENT)
        i.addCategory(Intent.CATEGORY_OPENABLE)
        if (fileChooserParams?.acceptTypes != null && fileChooserParams.acceptTypes.isNotEmpty()) {
            i.type = fileChooserParams.acceptTypes[0]
            if (i.type == "video/*") {
                videoFlag = true
            }

        } else {
            i.type = "*/*"
        }
        if (videoFlag) {
            recordVideo()
        } else {
            showPick()
        }
        return true
    }

    @HunterDebug
    override fun openFileChooser(
        uploadFile: ValueCallback<Uri>?,
        acceptType: String?,
        capture: String?
    ) {
        super.openFileChooser(uploadFile, acceptType, capture)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_FILE) {
            if (resultCode == RESULT_OK) {
//                setResultPic(data)
                var results: Array<Uri>? = null
                data?.let {
                    val t = it.dataString
                    results = arrayOf(Uri.parse(t))
                    mUri?.onReceiveValue(results)
                    mUri = null
                } ?: run {
                //setClear()
                    // }
                }
            } else if (resultCode == RESULT_CANCELED) {
                // setClear()
            } else {
                //  setClear()
            }
        }


        if (requestCode == TAKE_PHOTO_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                var results: Array<Uri>? = null
                mCameraUri?.let {
                    results = arrayOf(it)
                    mUri?.onReceiveValue(results)
                    mUri = null
                } ?: run {
                    //setClear()
                }
            } else if (resultCode == RESULT_CANCELED) {
               // setClear()
            } else {
               // setClear()
            }
        }

        if (requestCode == REQUEST_CODE_VIDEO) {
            if (resultCode == RESULT_OK) {
                setResultVideo(data)
            } else if (resultCode == RESULT_CANCELED) {
               // setClear()
            } else {
                //setClear()
            }
        }
    }
    /**
     * 回调录像。
     */
    fun setResultVideo(data: Intent?) {
        var results: Array<Uri>?
        data?.let {
            val t = it.data
            results = arrayOf(t!!)
            mUri?.onReceiveValue(results)
            mUri = null
        } ?: run {
            //setClear()
        }
    }
    private fun recordVideo() {
//
        Intent(MediaStore.ACTION_VIDEO_CAPTURE).also { takeVideoIntent ->
            /* 调用前置摄像头 */
            when {
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1 && Build.VERSION.SDK_INT < Build.VERSION_CODES.O -> {
                    takeVideoIntent.putExtra(
                        "android.intent.extras.CAMERA_FACING",
                        CameraCharacteristics.LENS_FACING_FRONT
                    )
                }
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
                    takeVideoIntent.putExtra(
                        "android.intent.extras.CAMERA_FACING",
                        CameraCharacteristics.LENS_FACING_FRONT
                    )
                    takeVideoIntent.putExtra("android.intent.extra.USE_FRONT_CAMERA", true)
                }
                else -> takeVideoIntent.putExtra("android.intent.extras.CAMERA_FACING", 1)
            }

            /* 低质量模式 0，高质量模式 1*/
            takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0)
            //限制时长 s
            takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5)
            //开启摄像机
            takeVideoIntent.resolveActivity(activity!!.packageManager)?.also {
                activity.startActivityForResult(takeVideoIntent, REQUEST_CODE_VIDEO)
            }
        }
    }


    /**
     * 从相册选取
     */
    private fun choiceFromAlbum() {
        val choosePicIntent = Intent(Intent.ACTION_PICK, null)
        choosePicIntent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*")
        activity.startActivityForResult(choosePicIntent, REQUEST_CODE_FILE)
    }

    /**
     * 调起相机拍照
     */
    private fun openCamera() {
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        // 判断是否有相机
        if (captureIntent.resolveActivity(activity.packageManager) != null) {
            var photoFile: File? = null
            var photoUri: Uri? = null
            if (isAndroidQ) {
                // 适配android 10
                photoUri = createImageUri()
            } else {
                try {
                    photoFile = createImageFile()
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                if (photoFile != null) {
                    mCameraImagePath = photoFile.absolutePath
                    photoUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        //适配Android 7.0文件权限，通过FileProvider创建一个content类型的Uri
                        FileProvider.getUriForFile(
                            activity, "com.chiatai.iorder.fileprovider", photoFile
                        )
                    } else {
                        Uri.fromFile(photoFile)
                    }
                }
            }
            mCameraUri = photoUri
            if (photoUri != null) {
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                captureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
                activity.startActivityForResult(captureIntent, TAKE_PHOTO_REQUEST_CODE)
            }
        }
    }

    /**
     * 创建图片地址uri,用于保存拍照后的照片 Android 10以后使用这种方法
     */
    private fun createImageUri(): Uri? {
        val status: String = Environment.getExternalStorageState()
        // 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
        return if (status == Environment.MEDIA_MOUNTED) {
            activity!!.contentResolver.insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                ContentValues()
            )
        } else {
            activity!!.contentResolver.insert(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                ContentValues()
            )
        }
    }
    private fun showPick() {
        val arr = arrayOf("打开相册", "立即拍照")
        activity?.let {
            var flag = false
            val builder = AlertDialog.Builder(it)
            builder.setTitle("上传方式")
                .setItems(arr) { dialog, which ->
                    when (which) {
                        0 -> {
                            choiceFromAlbum()
                            flag = true
                        }
                        1 -> {
                            openCamera()
                            flag = true
                        }
                    }
                }.setOnDismissListener {
                    if (!flag) {
                        //setClear()
                    }
                }
            builder.create().show()
        } ?: throw IllegalStateException("Activity cannot be null")

    }
    /**
     * 创建保存图片的文件
     */
    @Throws(IOException::class)
    private fun createImageFile(): File? {
        val imageName: String =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val storageDir: File? = activity!!.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        if (!storageDir!!.exists()) {
            storageDir.mkdir()
        }
        val tempFile = File(storageDir, imageName)
        return if (Environment.MEDIA_MOUNTED != EnvironmentCompat.getStorageState(tempFile)) {
            null
        } else tempFile
    }
    companion object {
        private const val TAG = "BankVerifyFragment"

        const val REQUEST_CODE_FILE = 21
        const val REQUEST_CODE_VIDEO = 1
        const val TAKE_PHOTO_REQUEST_CODE = 22
        const val loanType = "BankVerify"
    }
}