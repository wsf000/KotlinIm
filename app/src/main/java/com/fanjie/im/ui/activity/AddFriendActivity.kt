package com.fanjie.im.ui.activity

import android.support.v7.widget.LinearLayoutManager
import com.fanjie.im.R
import com.fanjie.im.adapter.AddFriendAdapter
import com.fanjie.im.base.BaseActivity
import com.fanjie.im.contract.AddFriendContract
import com.fanjie.im.presenter.AddFriendPrsenter
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.toast

/**
 * Created by shaofeng.wang on 2019/7/17.
 */
class AddFriendActivity : BaseActivity(), AddFriendContract.AddFriendView {

    val Presenter by lazy {
        AddFriendPrsenter(this)
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_add_friend
    }

    override fun initData() {
        super.initData()

        headerTitle.text = getString(R.string.add_friend)

        recyclerView.apply {
            //初始化
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = AddFriendAdapter(context,Presenter.AddFriendItems)

        }

        search.setOnClickListener { Search() }
    }

    private fun Search() {

        showProgress(getString(R.string.searching))
        val key = userName.text.trim().toString()
        Presenter.SearchFridend(key)
    }

    override fun SearchSuccess() {
        //搜索成功
        dismissProgress()
        toast(R.string.search_success)
        recyclerView.adapter.notifyDataSetChanged()

    }

    override fun SearchFiled() {
        dismissProgress()
        //搜索失败
        toast(R.string.search_failed)
    }
}