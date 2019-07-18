package com.fanjie.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanjie.im.model.data.ContactListItem
import com.fanjie.im.ui.activity.ChatActivity
import com.fanjie.im.widget.ContactListItemView
import org.jetbrains.anko.startActivity

/**
 * Created by shaofeng.wang on 2019/7/17.
 */
class ContactAdapter(val context: Context,val contactListItems: MutableList<ContactListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): RecyclerView.ViewHolder {
        return ContactAdapterViewhodler(ContactListItemView(context))

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val contactListItemView = holder.itemView as ContactListItemView
        contactListItemView.bindView(contactListItems[position])
        val userName = contactListItems[position].userName
        contactListItemView.setOnClickListener { context.startActivity<ChatActivity>("username" to userName) }


    }



    override fun getItemCount(): Int {

        return contactListItems.size
    }


    //创建viewHodler
    class ContactAdapterViewhodler(itemView: View?) : RecyclerView.ViewHolder(itemView) {


    }
}