package com.fanjie.im.ui.activity

import com.fanjie.im.R
import com.fanjie.im.base.BaseActivity
import com.fanjie.im.view.FragmentFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun getLayoutId(): Int {

        return R.layout.activity_main
    }

    override fun initData() {
        super.initData()

        bottomBar.setOnTabSelectListener { tabId: Int ->
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_frame,FragmentFactory.instance.getFragment(tabId))
            transaction.commit()
        }
    }

}
