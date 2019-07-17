package com.fanjie.im.ui.fragment

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.fanjie.im.R
import com.fanjie.im.adapter.ContactAdapter
import com.fanjie.im.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_contacts.*
import kotlinx.android.synthetic.main.header.*

/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class ContactFragmet : BaseFragment() {

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
        }

        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = ContactAdapter(context)
        }

    }
}