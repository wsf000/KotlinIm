package com.fanjie.im.contract

import com.fanjie.im.base.BasePresenter
import com.hyphenate.chat.EMMessage

/**
 * Created by shaofeng.wang on 2019/7/22.
 *
 * 发送消息的MVP协议类
 */
interface SendMessageContract {


    interface sendMessagePersenter : BasePresenter {

        fun sendMessage(contact: String, message: String)
        fun addmessage(username: String, p0: MutableList<EMMessage>?)
        fun loadMessage(username: String)
        fun loadMoreMessages(username: String)
    }


    interface senMessageView {

        fun startSendMessage()
        fun sendMessageSuccess()
        fun sendMessageFailed()
        fun onMessageLoaded()
        fun onMoreMessageLoaded(size: Int)
    }
}