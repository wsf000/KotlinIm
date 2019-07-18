package com.fanjie.im.contract

import com.fanjie.im.base.BasePresenter

/**
 * Created by shaofeng.wang on 2019/7/17.
 * 消息的MVP协议
 */
interface MessageContract {


    interface MessAgePresent :BasePresenter {

        fun messageload()

    }

    interface MessageView {



    }
}