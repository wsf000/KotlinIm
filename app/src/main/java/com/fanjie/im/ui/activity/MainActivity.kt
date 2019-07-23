package com.fanjie.im.ui.activity

import com.fanjie.im.R
import com.fanjie.im.base.BaseActivity
import com.fanjie.im.view.FragmentFactory
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMMessage
import com.itheima.im.adapter.EMMessageListenerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    val messagelisenter = object : EMMessageListenerAdapter() {
        override fun onMessageReceived(p0: MutableList<EMMessage>?) {
            runOnUiThread { updateBottomBarUnReadCount() }

        }
    }

    override fun getLayoutId(): Int {

        return R.layout.activity_main
    }

    override fun initData() {
        super.initData()

        bottomBar.setOnTabSelectListener { tabId: Int ->
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame, FragmentFactory.instance.getFragment(tabId))
            transaction.commit()
        }

        //设置消息监听器，时候监听消息个数显示
        EMClient.getInstance().chatManager().addMessageListener(messagelisenter)

    }

    override fun onResume() {
        super.onResume()

        //首页消息右上角个数显示

        updateBottomBarUnReadCount()
    }

    private fun updateBottomBarUnReadCount() {
        val tabWithId = bottomBar.getTabWithId(R.id.tab_conversation)
        tabWithId.setBadgeCount(EMClient.getInstance().chatManager().unreadMessageCount)
    }

    override fun onDestroy() {
        super.onDestroy()
        EMClient.getInstance().chatManager().removeMessageListener(messagelisenter)
    }

}
