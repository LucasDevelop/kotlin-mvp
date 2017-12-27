package com.mmy.maimaiyun

import android.os.Handler
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * @创建者     lucas
 * @创建时间   2017/12/26 0026 10:58
 * @描述          TODO
 */
@Module
class AppModule(private val app: App) {

    @Provides
    fun provideApp() = app

    @Provides
    @Singleton
    fun provideHandler() = Handler()

    @Provides
    @Singleton
    fun provideGson() = Gson()
}