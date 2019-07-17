package com.fanjie.im.view

import android.support.v4.app.Fragment
import com.fanjie.im.R
import com.fanjie.im.ui.fragment.ContactFragmet
import com.fanjie.im.ui.fragment.DynamicFragmet
import com.fanjie.im.ui.fragment.MessageFragmet

/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class FragmentFactory private constructor() {


    val message by lazy {
        MessageFragmet()
    }
    val contacts by lazy {
        ContactFragmet()
    }
    val dynamic by lazy {
        DynamicFragmet()
    }





    companion object {
        val instance = FragmentFactory()
    }

    fun getFragment(tabId :Int) :Fragment ?{

        when(tabId) {
            R.id.tab_conversation-> return  message
            R.id.tab_contacts -> return  contacts
            R.id.tab_dynamic -> return  dynamic

        }

        return  null
    }

}