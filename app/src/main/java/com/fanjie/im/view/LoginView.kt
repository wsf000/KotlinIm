package com.fanjie.im.view

/**
 * Created by shaofeng.wang on 2019/7/12.
 */
interface LoginView {

    fun onUsernameError()
    fun onPasswordError()
    fun startLogin()
    fun onLoginSuccesss()
    fun onLoginFailed()

}