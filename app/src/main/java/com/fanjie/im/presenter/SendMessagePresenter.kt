package com.fanjie.im.presenter

import com.fanjie.im.contract.SendMessageContract
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.itheima.im.adapter.EMCallBackAdapter
import org.jetbrains.anko.doAsync


/**
 * Created by shaofeng.wang on 2019/7/22.
 */
class SendMessagePresenter(val view: SendMessageContract.senMessageView) : SendMessageContract.sendMessagePersenter {

    val messages = mutableListOf<EMMessage>()
    companion object {
        val PAGE_SIZE = 10
    }

    override fun sendMessage(contact: String, message: String) {
        //创建一条消息
        val emMessage = EMMessage.createTxtSendMessage(message, contact)
        emMessage.setMessageStatusCallback(object : EMCallBackAdapter() {
            override fun onSuccess() {
                uiThread { view.sendMessageSuccess() }
            }

            override fun onError(p0: Int, p1: String?) {
                uiThread { view.sendMessageFailed() }
            }
        })
        messages.add(emMessage)
        view.startSendMessage()
        EMClient.getInstance().chatManager().sendMessage(emMessage)
    }

    override fun addmessage(username: String, p0: MutableList<EMMessage>?) {
        //加入当前的消息列表
        p0?.let { messages.addAll(it) }

        //更新消息为已读消息
        //获取跟联系人的会话，然后标记会话里面的消息为全部已读
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
        conversation.markAllMessagesAsRead()

    }

    override fun loadMessage(username: String) {
        doAsync {
        val conversation = EMClient.getInstance().chatManager().getConversation(username)
            messages.addAll(conversation.allMessages)
            uiThread { view.onMessageLoaded() }
        }
    }

    override fun loadMoreMessages(username: String) {

        doAsync {
            val conversation = EMClient.getInstance().chatManager().getConversation(username)
            val startMsgId = messages[0].msgId
            val loadMoreMsgFromDB = conversation.loadMoreMsgFromDB(startMsgId, PAGE_SIZE)
            messages.addAll(0, loadMoreMsgFromDB)
            uiThread { view.onMoreMessageLoaded(loadMoreMsgFromDB.size) }
        }
    }
}