package com.mmy.maimaiyun.helper

import android.widget.Toast
import com.blankj.utilcode.util.LogUtils
import com.mmy.maimaiyun.App


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 16:33
 * @描述         通用型工具
 */
interface CommentHelper {

    /**
     * 扩展函数
     * 对Any对象扩展toString方法，消除空指针异常
     */
    fun Any?.t(): String = if (this == null) "null" else toString()

    /**
     * 对Any对象扩展log方法
     */
    fun Any?.ld(tag: String = "lucas") {
        LogUtils.d(tag, this.toString())
    }

    fun Any?.le(tag: String = "lucas") {
        LogUtils.e(tag, this.toString())
    }

    fun String.showToast(app: App) {
        app.handler.post { Toast.makeText(app, this, Toast.LENGTH_SHORT) }
    }
}