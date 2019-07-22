package com.fanjie.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.fanjie.im.R
import com.hyphenate.chat.EMMessage
import com.hyphenate.chat.EMTextMessageBody
import com.hyphenate.util.DateUtils
import kotlinx.android.synthetic.main.view_receive_message_item.view.*
import java.util.*

/**
 * Created by shaofeng.wang on 2019/7/22.
 *
 *接收消息的条目界面
 */
class ReceiveMessageListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {


    init {
        View.inflate(context, R.layout.view_receive_message_item, this)
    }

    fun bindview(emMessage: EMMessage, showTimestamp: Boolean) {

        //接收消息的数据绑定
        uodatetime(emMessage,showTimestamp)
        updatemessage(emMessage)

    }

    private fun updatemessage(emMessage: EMMessage) {

        if (emMessage.type == EMMessage.Type.TXT) {
            receiveMessage.text = (emMessage.body as EMTextMessageBody).message
        } else{
            receiveMessage.text = context.getString(R.string.no_text_message)
        }
    }

    private fun uodatetime(emMessage: EMMessage, showTimestamp: Boolean) {

        if (showTimestamp) {
            timestamp.visibility = View.VISIBLE
            timestamp.text = DateUtils.getTimestampString(Date(emMessage.msgTime))
        } else timestamp.visibility = View.GONE

    }

}