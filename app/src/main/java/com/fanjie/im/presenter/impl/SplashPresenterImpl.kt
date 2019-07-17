package com.fanjie.im.presenter.impl

import com.fanjie.im.presenter.inter.SplashPresenter
import com.fanjie.im.view.SplashView
import com.hyphenate.chat.EMClient

/**
 * Created by shaofeng.wang on 2019/7/12.
 */
class SplashPresenterImpl(val splashview: SplashView) : SplashPresenter {


    override fun checkNotLogin() {
        if (isLogin()) splashview.onLogin() else splashview.onNotLogin()

    }

    private fun isLogin(): Boolean {
        return EMClient.getInstance().isConnected && EMClient.getInstance().isLoggedInBefore
    }
}