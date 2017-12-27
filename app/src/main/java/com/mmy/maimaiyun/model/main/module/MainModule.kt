package com.mmy.maimaiyun.model.main.module

import com.mmy.maimaiyun.model.main.view.MainView
import dagger.Module
import dagger.Provides


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:42
 * @描述          TODO
 */
@Module
class MainModule(private val mainView: MainView){
    @Provides
    fun provideView():MainView = mainView
}