package com.fanjie.im.ui.activity

import android.os.Handler
import com.fanjie.im.R
import com.fanjie.im.base.BaseActivity
import com.fanjie.im.presenter.impl.SplashPresenterImpl
import com.fanjie.im.view.SplashView
import org.jetbrains.anko.startActivity

/**
 * Created by shaofeng.wang on 2019/7/12.
 */
class SplashActivity : BaseActivity(), SplashView {

    val persenter = SplashPresenterImpl(this)

    val handler by lazy {
        Handler()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun onNotLogin() {
        //没有登录 （延迟2秒）
        handler.postDelayed({
            startActivity<LoginActivity>()
            finish()
        }, 2000)

    }

    override fun onLogin() {
        //已经登录
        startActivity<MainActivity>()
        finish()
    }

    override fun initData() {
        super.initData()
        persenter.checkNotLogin()
    }

    override fun initview() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}