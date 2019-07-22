package com.fanjie.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fanjie.im.R
import com.fanjie.im.adapter.ContactAdapter
import com.fanjie.im.base.BaseFragment
import com.fanjie.im.contract.ContactContract
import com.fanjie.im.presenter.ContactPresenter
import com.fanjie.im.ui.activity.AddFriendActivity
import com.hyphenate.chat.EMClient
import com.itheima.im.adapter.EMContactListenerAdapter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class ContactFragmet : BaseFragment(), ContactContract.ContactView {

    override fun getLayoutResId(): Int = R.layout.fragment_contacts

    val presenter = ContactPresenter(this)


    companion object {


    }


    val contactListener = object : EMContactListenerAdapter() {
        override fun onContactAdded(p0: String?) {
            //重新获取联系人数据
            presenter.LoadContact()
        }
    }

    override fun initData() {
        super.initData()
        initHeader()
        initSwipeRefreshLayout()
        initRecyclerView()
        EMClient.getInstance().contactManager().setContactListener(contactListener)
        presenter.LoadContact()
    }

    private fun initRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactAdapter(context, presenter.contactListItems)
        }
    }

    private fun initSwipeRefreshLayout() {
        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true
            setOnRefreshListener { presenter.LoadContact() }
        }
    }

    private fun initHeader() {
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE
        add.setOnClickListener { context!!.startActivity<AddFriendActivity>() }
    }

    override fun LoadContactFiled() {
        swipeRefreshLayout.isRefreshing = false
        context!!.toast(R.string.load_contacts_failed)
    }

    override fun LoadContactSuccess() {
        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter.notifyDataSetChanged()
    }

}