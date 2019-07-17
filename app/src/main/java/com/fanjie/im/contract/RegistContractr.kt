package com.fanjie.im.contract

import com.fanjie.im.base.BasePresenter

/**
 * Created by shaofeng.wang on 2019/7/15.
 * 注册界面的mvp协议，为了方便来管理Model、View、Presenter
 */
interface RegistContractr {

    interface presenter : BasePresenter {
        fun register(usrename :String ,password :String ,configpassword : String)
    }


    interface registview {

        fun onUsernameError()
        fun onPasswordError()
        fun onConfigPassword()
        fun onStartRegist()
        fun onRegistsuccess()
        fun onRegisterror()
        fun onHaveUser()
    }


}