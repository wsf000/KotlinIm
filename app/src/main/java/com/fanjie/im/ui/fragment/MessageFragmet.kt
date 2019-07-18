package com.fanjie.im.ui.fragment

import com.fanjie.im.R
import com.fanjie.im.base.BaseFragment
import kotlinx.android.synthetic.main.header.*

/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class MessageFragmet : BaseFragment() {

    override fun getLayoutResId(): Int {
      return  R.layout.fragment_conversation
    }


    override fun initData() {
        super.initData()

        headerTitle.text = getString(R.string.message)

    }
}