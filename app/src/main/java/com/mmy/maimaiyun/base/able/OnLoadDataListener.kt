package com.mmy.maimaiyun.base.able

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.mmy.maimaiyun.data.bean.IBean
import com.mmy.maimaiyun.helper.CommentHelper
import java.lang.reflect.Type


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 15:36
 * @描述          TODO
 */
open abstract class OnLoadDataListener<D> : CommentHelper {
    val tag: String = "lucas_net"
    abstract fun onSuccess(body: String, data: D?, iBean: IBean)
    abstract fun onFailed(body: String?, t: Throwable)
    abstract fun getBeanType(): Type

    open fun onStart() {}
    open fun onCompleted() {}

    /**
     * 解析服务器返回的json数据
     * 为什么要放到监听里面来 -> 原先是放在BaseModel里的，由于获取Bean的泛型太过复杂，所以已至此处。
     */
    fun parseJson(body: String, gson: Gson, context: Context) {
        var bean: D? = null
        var msg: String? = null
        var iBean: IBean? = null
        try {
            try {
                //使用IBean解析status和msg
                iBean = gson.fromJson(body, IBean::class.java)
            } catch (e: Exception) {
                "ibean 解析错误".le(tag)
                onFailed(body, e)
                return
            }
            //解析出data部分
            val jsonObject = JsonParser().parse(body).asJsonObject
            val dataValue = jsonObject.get("data")
            try {
                val type = getBeanType()
                if (type != null) {
                    bean = gson.fromJson(body, type)
                } else {
                    "getBeanType = null".le(tag)
                }
            } catch (e: Exception) {
                "data 解析失败".le(tag)
            }
            onSuccess(body, bean, iBean)
        } catch (e: Exception) {
            "gson 解析错误".le(tag)
            onFailed(body, e)
        } finally {
            onCompleted()
        }
    }
}