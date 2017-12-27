package com.mmy.maimaiyun.data.api.module

import com.google.gson.Gson
import com.mmy.maimaiyun.App
import com.mmy.maimaiyun.data.api.ApiService
import com.mmy.maimaiyun.utils.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:18
 * @描述          TODO
 */
@Module
class ApiServiceModule @Inject constructor() {
    @Singleton
    @Provides
    fun provideOkHttpClient(cache: Cache): OkHttpClient =
            OkHttpClient.Builder()
                    .readTimeout(Constants.READ_TIME_OUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(Constants.CONN_TIME_OUT, TimeUnit.MILLISECONDS)
                    .cache(cache)
                    .build()

    @Singleton
    @Provides
    fun provideCache(app: App):Cache =
            Cache(app.cacheDir,Constants.CACHE_SIZE)

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient,gson: Gson):Retrofit =
            Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit):ApiService =
            retrofit.create(ApiService::class.java)
}