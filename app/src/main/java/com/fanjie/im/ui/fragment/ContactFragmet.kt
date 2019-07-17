package com.fanjie.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fanjie.im.R
import com.fanjie.im.adapter.ContactAdapter
import com.fanjie.im.base.BaseFragment
import com.fanjie.im.contract.ContactContract
import com.fanjie.im.presenter.ContactPresenter
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class ContactFragmet : BaseFragment() ,ContactContract.ContactView{

    val preaenter by lazy {
        ContactPresenter(this)
    }

    override fun getLayoutResId(): Int {
      return R.layout.fragment_contacts

    }


    override fun initData() {
        super.initData()
        headerTitle.text = getString(R.string.contact)
        add.visibility = View.VISIBLE


        swipeRefreshLayout.apply {
            setColorSchemeResources(R.color.qq_blue)
            isRefreshing = true

            setOnRefreshListener { preaenter.LoadContact() }
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactAdapter(context)
        }

        preaenter.LoadContact()

    }

    override fun LoadContactSuccess() {

        swipeRefreshLayout.isRefreshing = false
        recyclerView.adapter.notifyDataSetChanged()

    }

    override fun LoadContactFiled() {

        swipeRefreshLayout.isRefreshing = false
        toast(R.string.load_contacts_failed)
    }
}