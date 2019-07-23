package com.fanjie.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import com.fanjie.im.R
import com.fanjie.im.adapter.MessageAdapter
import com.fanjie.im.base.BaseFragment
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMConversation
import com.hyphenate.chat.EMMessage
import com.itheima.im.adapter.EMMessageListenerAdapter
import kotlinx.android.synthetic.main.fragment_conversation.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class MessageFragmet : BaseFragment() {

    val conversations = mutableListOf<EMConversation>()


    val messageListener = object : EMMessageListenerAdapter() {


        override fun onMessageReceived(p0: MutableList<EMMessage>?) {

            loadConversations()
        }

    }

    private fun loadConversations() {
        doAsync {
            //清空会话列表
            conversations.clear()
            val allConversations = EMClient.getInstance().chatManager().allConversations
            conversations.addAll(allConversations.values)
            uiThread { recyclerView.adapter.notifyDataSetChanged() }
        }

    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_conversation
    }


    override fun initData() {
        super.initData()

        headerTitle.text = getString(R.string.message)

        initRecyleview()

        EMClient.getInstance().chatManager().addMessageListener(messageListener)

    }

    private fun initRecyleview() {

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = MessageAdapter(context, conversations)

        }
    }

    override fun onResume() {
        super.onResume()
        loadConversations()
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messageListener)
    }
}