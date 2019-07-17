package com.fanjie.im.ui.activity

import com.fanjie.im.R
import com.fanjie.im.base.BaseActivity
import com.fanjie.im.presenter.impl.LoginPresenterImpl
import com.fanjie.im.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by shaofeng.wang on 2019/7/12.
 */
class LoginActivity : BaseActivity(), LoginView {

    val loginimpl = LoginPresenterImpl(this)

    override fun getLayoutId(): Int {

        return R.layout.activity_login
    }

    override fun initData() {
        super.initData()

        login.setOnClickListener {
            login()
        }
        newUser.setOnClickListener {
            //跳转至注册界面
            startActivity<RegistActivity>()
        }

        password.setOnEditorActionListener { p0, p1, p2 ->
            login()
            true
        }
    }

    fun login() {

        val username = userName.text.trim().toString()
        val password = password.text.trim().toString()
        loginimpl.login(username,password)

    }


    override fun onUsernameError() {
        userName.error = getString(R.string.user_name_error)
    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun startLogin() {
        //弹出进度条
        showProgress(getString(R.string.logging))

    }

    override fun onLoginSuccesss() {
        dismissProgress()

        startActivity<MainActivity>()

        finish()
    }

    override fun onLoginFailed() {

        dismissProgress()

        toast(R.string.login_failed)
    }
}