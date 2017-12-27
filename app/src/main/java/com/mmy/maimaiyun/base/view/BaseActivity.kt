package com.mmy.maimaiyun.base.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.pedant.SweetAlert.SweetAlertDialog
import com.mmy.maimaiyun.App
import com.mmy.maimaiyun.AppComponent
import com.mmy.maimaiyun.base.mvp.IPresenter
import com.mmy.maimaiyun.base.mvp.IView
import com.mmy.maimaiyun.helper.CommentHelper
import javax.inject.Inject


/**
 * @创建者     lucas
 * @创建时间   2017/12/25 0025 13:42
 * @描述          所有activity应该集成该类
 */
open abstract class BaseActivity<V : IView, P : IPresenter<V>> : AppCompatActivity(), IView,CommentHelper {

    @Inject lateinit var mIPresenter: P

    var loadingDialog: SweetAlertDialog? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        setupDagger((application as App).appComponent)
        initComment()
        initView()
        initData()
        initEvent()
    }

    private fun initComment() {
        loadingDialog = SweetAlertDialog(this, SweetAlertDialog.PROGRESS_TYPE)
        loadingDialog?.titleText = "加载中.."
    }

    abstract fun setupDagger(appComponent: AppComponent)

    abstract fun initView()

    abstract fun initData()

    abstract fun initEvent()

    //布局id
    abstract fun getLayoutID(): Int



    override fun showLoading() {
        if (!loadingDialog?.isShowing!!)//!!loading为空时会出空指针异常
            loadingDialog?.show()
    }

    override fun hidLoading() {
        if (loadingDialog?.isShowing!!) {
            loadingDialog?.dismiss()
        }
    }

    override fun <A : Activity> openActivity(activity: Class<A>, params: String, isResult: Boolean,requestCode:Int) {
        val intent = Intent(this, activity)
        val list = params.split(",")
        list.forEach {
            val split = it.split("=")
            if (split.size == 2)
                intent.putExtra(split[0], split[1])
        }
        if (isResult)
            startActivity(intent)
        else
            startActivityForResult(intent, requestCode)
    }
}