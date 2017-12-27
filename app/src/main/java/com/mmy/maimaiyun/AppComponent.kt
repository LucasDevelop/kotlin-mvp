package com.mmy.maimaiyun

import android.os.Handler
import com.google.gson.Gson
import com.mmy.maimaiyun.data.api.ApiService
import com.mmy.maimaiyun.data.api.module.ApiServiceModule
import dagger.Component
import javax.inject.Singleton


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:12
 * @描述          TODO
 */
@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ApiServiceModule::class
))
interface AppComponent {
    fun getApp():App
    fun getHandler():Handler
    fun getApiService():ApiService
    fun getGson():Gson
    fun inject(app: App)
}