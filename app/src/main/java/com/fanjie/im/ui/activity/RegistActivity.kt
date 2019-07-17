package com.fanjie.im.ui.activity

import com.fanjie.im.R
import com.fanjie.im.base.BaseActivity
import com.fanjie.im.contract.RegistContractr
import com.fanjie.im.presenter.registpresenter
import com.fanjie.im.view.Registview
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class RegistActivity : BaseActivity(), RegistContractr.registview {


    val persenter by lazy {
        registpresenter(this)
    }


    override fun getLayoutId(): Int {

        return R.layout.activity_register

    }

    override fun initData() {
        super.initData()
        register.setOnClickListener {
            regist()
        }
    }

    private fun regist() {

        val name = userName.text.trim().toString()
        val ps = password.text.trim().toString()
        val confirm = confirmPassword.text.trim().toString()

        persenter.register(name, ps, confirm)
    }

    override fun onUsernameError() {

        userName.error = getString(R.string.user_name_error)

    }

    override fun onPasswordError() {
        password.error = getString(R.string.password_error)
    }

    override fun onConfigPassword() {
        confirmPassword.error = getString(R.string.confirm_password_error)

    }

    override fun onStartRegist() {

        showProgress(getString(R.string.logging))
    }

    override fun onRegistsuccess() {
        dismissProgress()
        finish()

    }

    override fun onRegisterror() {
        dismissProgress()
        toast(R.string.register_failed)
    }

    override fun onHaveUser() {
        dismissProgress()
        toast(R.string.user_already_exist)
    }
}