package com.fanjie.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanjie.im.widget.AddFriendListItemView
import com.itheima.im.data.AddFriendItem

/**
 * Created by shaofeng.wang on 2019/7/18.
 *
 * 添加好友的Apapter
 */
class AddFriendAdapter(val context: Context, val addFriendItems: MutableList<AddFriendItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return AddFriendAdapterViewHodler(AddFriendListItemView(context))

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        //绑定数据
        val addFriendListItemView = holder.itemView as AddFriendListItemView
        addFriendListItemView.bindView(addFriendItems[position])

    }


    override fun getItemCount(): Int {

        return addFriendItems.size
    }


    class AddFriendAdapterViewHodler(itemView: View?) : RecyclerView.ViewHolder(itemView) {


    }
}