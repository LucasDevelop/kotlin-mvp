package com.mmy.maimaiyun

import android.app.Application
import android.os.Handler
import com.blankj.utilcode.util.Utils
import com.mmy.maimaiyun.data.api.module.ApiServiceModule
import okhttp3.Cache
import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/23 0023 16:30
 * @描述          程序入口
 */
class App : Application() {

    @Inject lateinit var handler: Handler
    @Inject lateinit var app: App
    @Inject lateinit var cache:Cache

    var appComponent: AppComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .apiServiceModule(ApiServiceModule())
            .build()

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        appComponent.inject(this)
    }


}