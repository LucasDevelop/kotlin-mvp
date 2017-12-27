package com.mmy.maimaiyun.base.view

import android.app.Activity
import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmy.maimaiyun.App
import com.mmy.maimaiyun.AppComponent
import com.mmy.maimaiyun.base.mvp.IPresenter
import com.mmy.maimaiyun.base.mvp.IView
import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 15:20
 * @描述          TODO
 */
open abstract class BaseFragment<V : IView, P : IPresenter<V>> : Fragment(), IView {

    @Inject lateinit var mIPresenter: P

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        setupDagger((activity.application as App).appComponent)
        val inflate = LayoutInflater.from(activity).inflate(getLayoutId(), null, false)
        initView()
        initData()
        initEvent()
        return inflate
    }

    abstract fun setupDagger(appComponent: AppComponent)
    abstract fun getLayoutId(): Int
    abstract fun initView()
    abstract fun initData()
    abstract fun initEvent()


    override fun showLoading() {
        (activity as BaseActivity<*, *>).showLoading()
    }

    override fun hidLoading() {
        (activity as BaseActivity<*,*>).hidLoading()
    }

    override fun <A : Activity> openActivity(activity: Class<A>, params: String, isResult: Boolean, requestCode: Int) {
        (activity as BaseActivity<*, *>).openActivity(activity,params,isResult,requestCode)
    }
}