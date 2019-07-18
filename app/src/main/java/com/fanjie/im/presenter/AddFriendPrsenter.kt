package com.fanjie.im.presenter

import com.fanjie.im.contract.AddFriendContract
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.BmobUser
import cn.bmob.v3.BmobQuery
import cn.bmob.v3.listener.FindListener
import com.hyphenate.chat.EMClient
import com.itheima.im.data.AddFriendItem
import com.itheima.im.data.db.IMDatabase
import org.jetbrains.anko.doAsync


/**
 * Created by shaofeng.wang on 2019/7/18.
 */
class AddFriendPrsenter(val view: AddFriendContract.AddFriendView)
/** p层需要view层的引用去更新view层*/
    : AddFriendContract.AddFriendPresenter {

    val AddFriendItems = mutableListOf<AddFriendItem>()

    override fun SearchFridend(key: String) {
        //搜索   model层该做
        val query = BmobQuery<BmobUser>()

        query.addWhereContains("username", key)
                .addWhereNotEqualTo("username", EMClient.getInstance().currentUser)
//        query.addWhereEqualTo("username", key)
        query.findObjects(object : FindListener<BmobUser>() {
            override fun done(p0: MutableList<BmobUser>?, p1: BmobException?) {

                if (p1 == null) {

                    //获取数据库联系人进行比对
                    val contacts = IMDatabase.instance.getAllContacts()




                    doAsync {
                        //处理数据
                        p0?.forEach {
                            //比对是否已经添加过好友
                            var isAdd = false
                            for (contact in contacts) {
                                if (contact.name == it.username) {
                                    isAdd = true

                                }
                            }

                            val addFriendItem = AddFriendItem(it.username,it.createdAt,isAdd)
                            AddFriendItems.add(addFriendItem)


                        }

                        uiThread {
                            view.SearchSuccess()
                        }
                    }



                } else view.SearchFiled()

            }

        })

    }
}