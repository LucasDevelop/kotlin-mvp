package com.mmy.maimaiyun.base.mvp

import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/23 0023 16:30
 * @描述          TODO
 */
open class IPresenter<V : IView> {

    //注入View层
    @Inject lateinit var mV: V

    fun isAttach() = mV == null

}