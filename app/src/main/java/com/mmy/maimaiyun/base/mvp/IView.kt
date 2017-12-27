package com.mmy.maimaiyun.base.mvp

import android.app.Activity


/**
 * @创建者     lucas
 * @创建时间   2017/12/23 0023 16:28
 * @描述          TODO
 */
interface IView {
    fun showLoading()
    fun hidLoading()
    //打开一个activity
    //打开一个activity并且传入参数 格式 ：key=value,key=value...
    //是否返回数据
    fun <A : Activity> openActivity(activity: Class<A>, params: String = "", isResult: Boolean = false, requestCode: Int = 0)
}