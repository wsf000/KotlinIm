package com.itheima.im.adapter

import com.hyphenate.EMMessageListener
import com.hyphenate.chat.EMMessage


open class EMMessageListenerAdapter: EMMessageListener{
    override fun onMessageRecalled(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageChanged(p0: EMMessage?, p1: Any?) {
    }

    override fun onCmdMessageReceived(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageReceived(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageDelivered(p0: MutableList<EMMessage>?) {
    }

    override fun onMessageRead(p0: MutableList<EMMessage>?) {
    }

}