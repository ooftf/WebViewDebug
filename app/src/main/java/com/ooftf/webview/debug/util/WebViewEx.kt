package com.ooftf.webview.debug.util

import android.webkit.WebView
import okhttp3.FormBody
import okio.Buffer

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/5/27
 */
fun WebView.postUrl(url: String, param: Map<String, String>) {
    val buffer = Buffer()
    FormBody.Builder().apply {
        param.forEach {
            add(it.key, it.value)
        }
    }.build().writeTo(buffer)
    postUrl(url, buffer.readByteArray())
}

fun com.tencent.smtt.sdk.WebView.postUrl(url: String, param: Map<String, String>) {
    val buffer = Buffer()
    FormBody.Builder().apply {
        param.forEach {
            add(it.key, it.value)
        }
    }.build().writeTo(buffer)
    postUrl(url, buffer.readByteArray())
}