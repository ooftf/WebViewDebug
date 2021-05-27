package com.ooftf.webview.debug

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ooftf.webview.debug.databinding.ActivityLauncherBinding
import com.ooftf.webview.debug.original.OriginalWebActivity
import com.ooftf.webview.debug.x5.X5WebActivity
import com.tbruyelle.rxpermissions3.RxPermissions

/**
 *
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2021/5/14
 */
class LauncherActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLauncherBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLauncherBinding.inflate(layoutInflater)
        setContentView(binding.root)
        RxPermissions(this)
            .request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.ACCESS_FINE_LOCATION
            ).subscribe {
                if (!it) {
                    val rxPermissions = RxPermissions(this)
                    if (!rxPermissions.isGranted(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Toast.makeText(this, "获取文件权限失败！请在设置中授予权限", Toast.LENGTH_SHORT).show()
                    }
                    if (!rxPermissions.isGranted(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                        Toast.makeText(this, "获取定位权限失败！请在设置中授予权限", Toast.LENGTH_SHORT).show()
                    }
                    if (!rxPermissions.isGranted(Manifest.permission.CAMERA)) {
                        Toast.makeText(this, "获取相机权限失败！请在设置中授予权限", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        binding.original.setOnClickListener {
            startActivity(Intent(this,OriginalWebActivity::class.java))
        }
        binding.x5.setOnClickListener {
            startActivity(Intent(this,X5WebActivity::class.java))
        }
        WebView.setWebContentsDebuggingEnabled(true)
        com.tencent.smtt.sdk.WebView.setWebContentsDebuggingEnabled(true)
    }
}