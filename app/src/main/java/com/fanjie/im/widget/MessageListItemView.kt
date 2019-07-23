package com.fanjie.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.fanjie.im.R
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_conversation_item.view.*
import java.util.*

/**
 * Created by shaofeng.wang on 2019/7/22.
 */
class MessageListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {


    init {
        View.inflate(context, R.layout.view_conversation_item, this)
    }

    fun bindView(emConversation: EMConversation) {
        userName.text = emConversation.conversationId()
        if (emConversation.lastMessage.type == EMMessage.Type.TXT){
            val body = emConversation.lastMessage.body as EMTextMessageBody
            lastMessage.text = body.message
        } else lastMessage.text = context.getString(R.string.no_text_message)
        val timestampString = DateUtils.getTimestampString(Date(emConversation.lastMessage.msgTime))
        timestamp.text = timestampString

        if (emConversation.unreadMsgCount > 0) {
            unreadCount.visibility = View.VISIBLE
            unreadCount.text = emConversation.unreadMsgCount.toString()
        } else {
            unreadCount.visibility = View.GONE
        }
    }

}