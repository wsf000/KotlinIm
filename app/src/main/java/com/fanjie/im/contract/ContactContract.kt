package com.fanjie.im.contract

import com.fanjie.im.base.BasePresenter

/**
 * Created by shaofeng.wang on 2019/7/17.
 * 联系人的mvp协议
 */
interface ContactContract {


    interface ContactPresenter :BasePresenter {
        fun LoadContact()
    }

    interface ContactView {
        fun LoadContactSuccess()
        fun LoadContactFiled()
    }


}