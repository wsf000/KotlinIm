package com.fanjie.im.presenter.inter

import com.fanjie.im.base.BasePresenter

/**
 * Created by shaofeng.wang on 2019/7/12.
 */
interface LoginPresenter : BasePresenter{
    //

    fun login(username : String , password : String)

}