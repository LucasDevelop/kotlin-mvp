package com.mmy.maimaiyun.data.api

import com.mmy.maimaiyun.utils.Constants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:18
 * @描述          TODO
 */
interface ApiService {

    @GET(Constants.VERSION)
    fun checkVersion(): Call<ResponseBody>
}