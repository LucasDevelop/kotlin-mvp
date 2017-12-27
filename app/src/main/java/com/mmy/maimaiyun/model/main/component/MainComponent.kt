package com.mmy.maimaiyun.model.main.component

import com.mmy.maimaiyun.AppComponent
import com.mmy.maimaiyun.base.annotation.ActivityScope
import com.mmy.maimaiyun.model.main.acitvity.MainActivity
import com.mmy.maimaiyun.model.main.module.MainModule
import dagger.Component


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 11:51
 * @描述          TODO
 */
@ActivityScope
@Component(modules = arrayOf(MainModule::class),dependencies = arrayOf(AppComponent::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}