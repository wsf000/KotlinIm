package com.fanjie.im.ui.fragment

import com.fanjie.im.R
import com.fanjie.im.base.BaseFragment
import com.fanjie.im.contract.DynamicContract
import com.fanjie.im.presenter.DynamicPresenter
import com.fanjie.im.ui.activity.LoginActivity
import kotlinx.android.synthetic.main.fragment_dynamic.*
import kotlinx.android.synthetic.main.header.*
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class DynamicFragmet : BaseFragment() ,DynamicContract.Dynamicview{


    val presenter by lazy {
        DynamicPresenter(this)
    }

    override fun getLayoutResId(): Int {
      return R.layout.fragment_dynamic
    }


    override fun initData() {
        super.initData()

        //界面显示
        headerTitle.text = getString(R.string.dynamic)

        logout.setOnClickListener {
            presenter.exitAPP()
        }

    }

    override fun onexutAppsuccess() {
        context!!.runOnUiThread {
            toast(R.string.logout_success)
            context!!.startActivity<LoginActivity>()
            activity!!.finish()
        }

    }

    override fun onexitAPPerror() {


        //切换到主线程
        context!!.runOnUiThread { toast(R.string.logout_failed) }
    }
}