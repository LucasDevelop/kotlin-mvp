package com.mmy.maimaiyun.base.mvp

import com.blankj.utilcode.util.NetworkUtils
import com.google.gson.Gson
import com.mmy.maimaiyun.App
import com.mmy.maimaiyun.base.able.OnLoadDataListener
import com.mmy.maimaiyun.data.api.ApiService
import com.mmy.maimaiyun.helper.CommentHelper
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Call
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 15:33
 * @描述          数据层基类
 */
open abstract class BaseModel : CommentHelper {


    @Inject lateinit var apiService: ApiService

    @Inject lateinit var gson: Gson

    @Inject lateinit var app: App

    val tag: String = "lucas_net"

    /**
     * 通用网络请求封装
     */
    fun wrapRequest(listener: OnLoadDataListener<*>, call: Call<ResponseBody>) {
        //判断网络是否可用
        if (!NetworkUtils.isConnected()) {
            "网络不可用".ld(tag)
            listener.onFailed(null, RuntimeException("网络不可用"))
            "网络不可用".showToast(app)
        }

        Observable.just(call)
                .map({
                    listener.onStart()
                    return@map getResponseBody(it, listener)
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    //解析json
                    listener.parseJson(it,gson,app)
                }, {
                    //处理异常
                    listener.onFailed(null,it)
                    "rxjava 错误".le(tag)
                    it.printStackTrace()
                })
    }

    /**
     * 网络请求细节
     */
    fun getResponseBody(call: Call<ResponseBody>, listener: OnLoadDataListener<*>): String {
        val response = call.execute()
        val code = response.code()
        var body: String = ""
        //判断状态码
        try {
            if (code != 200) {
                "net url:${call.request()}\nstatus code:$code".ld(tag)
                if (code >= 400) {//服务器错误
                    "net url:${call.request()}\n服务器错误 code:$code".le(tag)
                    listener.onFailed(response.body().string(), RuntimeException("服务器错误"))
                    return body
                }
            }
            //获取数据
            body = response.body().string()
            "net url:${call.request()}\njson:$body".ld(tag)
        } catch (e: SocketTimeoutException) {
            "服务器响应超时".le(tag)
            "服务器响应超时".showToast(app)
            e.printStackTrace()
            listener.onFailed(body, e)
            call.cancel()
        } catch (e: IOException) {
            e.printStackTrace()
            "服务器错误".le(tag)
            listener.onFailed(body, e)
        } finally {
            return body
        }
    }


}