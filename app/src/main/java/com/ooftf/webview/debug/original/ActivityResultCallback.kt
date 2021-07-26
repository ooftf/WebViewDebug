package com.ooftf.webview.debug.original

import android.content.Intent

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/7/26
 */
interface ActivityResultCallback {
    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
}