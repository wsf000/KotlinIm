package com.fanjie.im.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.fanjie.im.R
import com.fanjie.im.adapter.MessageListAdapter
import com.fanjie.im.base.BaseActivity
import com.fanjie.im.contract.SendMessageContract
import com.fanjie.im.presenter.SendMessagePresenter
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.itheima.im.adapter.EMMessageListenerAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * Created by shaofeng.wang on 2019/7/17.
 * 聊天界面
 */
class ChatActivity : BaseActivity(), SendMessageContract.senMessageView {
    val presentre by lazy {
        SendMessagePresenter(this)
    }

    val msgListener = object : EMMessageListenerAdapter() {

        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            presentre.addmessage(username, p0)

            runOnUiThread {
                recyclerView.adapter.notifyDataSetChanged()
                scrollbottom()
            }

        }
    }

    lateinit var username: String


    override fun getLayoutId(): Int {
        return R.layout.activity_chat
    }


    override fun initData() {
        super.initData()

        initTitle()
        initEdittext()
        initRecyleview()
        EMClient.getInstance().chatManager().addMessageListener(msgListener)//接收消息的监听

        send.setOnClickListener {
            sendMes()
        }
        presentre.loadMessage(username)

    }

    private fun initRecyleview() {

        recyclerView.apply {
            recyclerView.setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = MessageListAdapter(context, presentre.messages)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                    //当RecyclerView是一个空闲的状态
                    //检查是否滑到顶部，要加载更多数据
                    if (newState == RecyclerView.SCROLL_STATE_IDLE){
                        //如果第一个可见条目的位置是0, 为滑到顶部
                        val linearLayoutManager = layoutManager as LinearLayoutManager
                        if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                            //加载更多数据
                            presentre.loadMoreMessages(username)
                        }
                    }
                }
            })

        }
    }

    private fun sendMes() {
        val cont = edit.text.toString().trim()

        presentre.sendMessage(username, cont)
    }

    private fun initEdittext() {

        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                send.isEnabled = !p0.isNullOrEmpty()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

        })
    }

    private fun initTitle() {
        back.visibility = View.VISIBLE
        back.setOnClickListener { finish() }
        //获取好友列表传过来的username
        username = intent.getStringExtra("username")
        val titlename = String.format(getString(R.string.chat_title), username)

        headerTitle.text = titlename
    }

    override fun startSendMessage() {

        recyclerView.adapter.notifyDataSetChanged()

    }

    override fun sendMessageSuccess() {

        recyclerView.adapter.notifyDataSetChanged()

        toast(R.string.send_message_success)

        edit.text.clear()

        scrollbottom()
    }

    override fun onMessageLoaded() {
        recyclerView.adapter.notifyDataSetChanged()
        scrollbottom()

    }

    override fun onMoreMessageLoaded(size: Int) {
        recyclerView.adapter.notifyDataSetChanged()
        recyclerView.scrollToPosition(size)

    }

    private fun scrollbottom() {
        //让消息一直滚动到底部
        recyclerView.scrollToPosition(presentre.messages.size - 1)

    }

    override fun sendMessageFailed() {

        toast(R.string.send_message_failed)


    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(msgListener);
    }
}