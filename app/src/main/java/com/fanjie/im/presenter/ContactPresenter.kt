package com.fanjie.im.presenter

import com.fanjie.im.contract.ContactContract
import com.fanjie.im.model.data.ContactListItem
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import com.itheima.im.data.db.Contact
import com.itheima.im.data.db.IMDatabase
import org.jetbrains.anko.doAsync


/**
 * Created by shaofeng.wang on 2019/7/17.
 */
class ContactPresenter(val view: ContactContract.ContactView) : ContactContract.ContactPresenter {
    val contactListItems = mutableListOf<ContactListItem>()
    override fun LoadContact() {


        doAsync {
            //再次加载数据，先清空集合
            contactListItems.clear()

            IMDatabase.instance.deleteAllContacts()

            try {
                val usernames = EMClient.getInstance().contactManager().allContactsFromServer
                usernames.forEachIndexed { index, s ->
                    //判断是否显示首字符
                    val showFirstLetter = index == 0 || s[0] != usernames[index - 1][0]
                    val contactListItem = ContactListItem(s, s[0].toUpperCase(), showFirstLetter)
                    contactListItems.add(contactListItem)

                    //创建一个联系人
                    val contact = Contact(mutableMapOf("name" to s))
                    IMDatabase.instance.saveContact(contact)//保存联系人到数据库


                }

                uiThread { view.LoadContactSuccess() }
            } catch (e: HyphenateException) {
                uiThread { view.LoadContactFiled() }

            }

        }
    }


}