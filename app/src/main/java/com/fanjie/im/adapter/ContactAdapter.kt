package com.fanjie.im.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.fanjie.im.widget.ContactListItemView

/**
 * Created by shaofeng.wang on 2019/7/17.
 */
class ContactAdapter(val context : Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): RecyclerView.ViewHolder {
        return ContactAdapterViewhodler(ContactListItemView(context))

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }



    override fun getItemCount(): Int {

        return 30
    }


    //创建viewHodler
    class ContactAdapterViewhodler(itemView: View?) : RecyclerView.ViewHolder(itemView) {


    }
}