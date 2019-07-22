package com.fanjie.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanjie.im.widget.ReceiveMessageListItemView
import com.fanjie.im.widget.SendMessageListItemView
import com.hyphenate.chat.EMMessage
import com.hyphenate.util.DateUtils

/**
 * Created by shaofeng.wang on 2019/7/22.
 */
class MessageListAdapter(val context: Context, val messages: List<EMMessage>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //发送消息和接收消息的adapter
    companion object {
        val ITEM_TYPE_SEND_MESSAGE = 0
        val ITEM_TYPE_RECEIVE_MESSAGE = 1
    }

    override fun getItemViewType(position: Int): Int {

        if (messages[position].direct() == EMMessage.Direct.SEND) {
            return ITEM_TYPE_SEND_MESSAGE
        } else {
            return ITEM_TYPE_RECEIVE_MESSAGE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val showTimestamp = isShowTimestamp(position)

        if (getItemViewType(position) == ITEM_TYPE_SEND_MESSAGE) {
            val itemView = holder.itemView as SendMessageListItemView
            itemView.bindview(messages[position],showTimestamp)
        } else {
            val reciveitemview = holder.itemView as ReceiveMessageListItemView
            reciveitemview.bindview(messages[position],showTimestamp)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == ITEM_TYPE_SEND_MESSAGE) {
            return sendViewhodler(SendMessageListItemView(context))
        } else {
            return receiveViewhodler(ReceiveMessageListItemView(context))
        }


    }

    private fun isShowTimestamp(position: Int): Boolean {
        //如果是第一条消息或者和前一条消息间隔时间比较长 则不显示时间戳
        var showTimestamp = true
        if (position > 0) {
            showTimestamp = !DateUtils.isCloseEnough(messages[position].msgTime, messages[position - 1].msgTime)
        }
        return showTimestamp
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class sendViewhodler(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }

    class receiveViewhodler(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    }
}