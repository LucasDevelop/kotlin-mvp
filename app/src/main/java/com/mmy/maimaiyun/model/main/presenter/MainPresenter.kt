package com.mmy.maimaiyun.model.main.presenter

import com.google.gson.reflect.TypeToken
import com.mmy.maimaiyun.base.able.OnLoadDataListener
import com.mmy.maimaiyun.base.mvp.IPresenter
import com.mmy.maimaiyun.data.bean.IBean
import com.mmy.maimaiyun.helper.CommentHelper
import com.mmy.maimaiyun.model.main.model.MainModel
import com.mmy.maimaiyun.model.main.view.MainView
import java.lang.reflect.Type
import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:48
 * @描述          TODO
 */
class MainPresenter @Inject constructor():IPresenter<MainView>(),CommentHelper {

    @Inject lateinit var model:MainModel
    /**
     * 检查版本
     */
    fun checkVersion(){
        mV.showLoading()
        model.checkVersion(object : OnLoadDataListener<IBean>() {
            override fun onSuccess(body: String, data: IBean?, iBean: IBean) {

            }

            override fun onFailed(body: String?, t: Throwable) {
            }

            override fun onCompleted() {
                mV.hidLoading()
            }

            override fun getBeanType(): Type {
                return object :TypeToken<IBean>(){}.type
            }

        })
    }
}