package com.mmy.maimaiyun.model.main.model

import com.mmy.maimaiyun.base.able.OnLoadDataListener
import com.mmy.maimaiyun.base.mvp.BaseModel
import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/26 0026 11:23
 * @描述          TODO
 */
class MainModel @Inject constructor() : BaseModel() {


    fun checkVersion(onLoadDataListener: OnLoadDataListener<*>) {
        val call = apiService.checkVersion()
        wrapRequest(onLoadDataListener,call)
    }
}