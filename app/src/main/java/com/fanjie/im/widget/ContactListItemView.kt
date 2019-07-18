package com.fanjie.im.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.fanjie.im.R
import com.fanjie.im.model.data.ContactListItem
import kotlinx.android.synthetic.main.view_contact_item.view.*

/**
 * Created by shaofeng.wang on 2019/7/17.
 */
class ContactListItemView(context: Context?, attrs: AttributeSet? = null) : RelativeLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_contact_item, this)
    }

    fun bindView(contactListItem: ContactListItem) {

        if(contactListItem.showFirstLetter){
            firstLetter.visibility = View.VISIBLE
            firstLetter.text = contactListItem.firstLetter.toString()
        } else firstLetter.visibility = View.GONE
        userName.text = contactListItem.userName



    }


}