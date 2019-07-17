package com.fanjie.im.presenter

import com.fanjie.im.contract.ContactContract
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync


/**
 * Created by shaofeng.wang on 2019/7/17.
 */
class ContactPresenter(val view: ContactContract.ContactView) : ContactContract.ContactPresenter {
    override fun LoadContact() {

        doAsync {
            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                uiThread { view.LoadContactSuccess()}
            } catch (e: HyphenateException) {
                uiThread { view.LoadContactFiled() }

            }

        }
    }


}