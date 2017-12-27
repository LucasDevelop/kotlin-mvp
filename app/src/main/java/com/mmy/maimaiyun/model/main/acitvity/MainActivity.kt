package com.mmy.maimaiyun.model.main.acitvity

import com.mmy.maimaiyun.AppComponent
import com.mmy.maimaiyun.R
import com.mmy.maimaiyun.base.view.BaseActivity
import com.mmy.maimaiyun.model.main.component.DaggerMainComponent
import com.mmy.maimaiyun.model.main.module.MainModule
import com.mmy.maimaiyun.model.main.presenter.MainPresenter
import com.mmy.maimaiyun.model.main.view.MainView

class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {
    override fun getLayoutID(): Int = R.layout.activity_main

    override fun setupDagger(appComponent: AppComponent) {
        DaggerMainComponent
                .builder()
                .appComponent(appComponent)
                .mainModule(MainModule(this))
                .build().inject(this)
    }

    override fun initView() {

    }

    override fun initData() {
        mIPresenter.checkVersion()
    }

    override fun initEvent() {
    }

    override fun onVersionChange() {
    }


}
