package com.fanjie.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanjie.im.widget.MessageListItemView
import com.hyphenate.chat.EMConversation

/**
 * Created by shaofeng.wang on 2019/7/22.
 */
class MessageAdapter(val context: Context, val conversations: MutableList<EMConversation>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {




    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val conversationListItemView = holder.itemView as MessageListItemView
        conversationListItemView.bindView(conversations[position])


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ConversationListItemViewHolder(MessageListItemView(context))


    }

    override fun getItemCount(): Int = conversations.size

    class ConversationListItemViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}