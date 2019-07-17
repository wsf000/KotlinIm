package com.fanjie.im.presenter.impl

import com.fanjie.im.presenter.inter.LoginPresenter
import com.fanjie.im.ui.isValidPassword
import com.fanjie.im.ui.isValidUserName
import com.fanjie.im.view.LoginView
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient

/**
 * Created by shaofeng.wang on 2019/7/12.
 */
class LoginPresenterImpl(val loginview: LoginView) : LoginPresenter {

    override fun login(username: String, password: String) {

        if (username.isValidUserName()) {
//正确，下来密码
            if (password.isValidPassword()) {
                //开始登录
                loginview.startLogin()
                LoginIM(username, password)

            } else loginview.onPasswordError()

        } else loginview.onUsernameError()
    }

    private fun LoginIM(username: String, password: String) {

        EMClient.getInstance().login(username, password, object : EMCallBack {
            override fun onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();

                //在主线程通知View层
                uiThread { loginview.onLoginSuccesss() }
            }

            override fun onProgress(p0: Int, p1: String?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { loginview.onLoginFailed() }
            }
        })

    }
}