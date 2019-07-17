package com.fanjie.im.presenter

import com.fanjie.im.contract.DynamicContract
import com.hyphenate.EMCallBack
import com.hyphenate.chat.EMClient

/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class DynamicPresenter(val view: DynamicContract.Dynamicview) : DynamicContract.presenter {


    override fun exitAPP() {

        EMClient.getInstance().logout(true, object :
                EMCallBack {
            override fun onSuccess() {
                view.onexutAppsuccess()
                //从Im
            }

            override fun onProgress(p0: Int, p1: String?) {

            }

            override fun onError(p0: Int, p1: String?) {
                view.onexitAPPerror()
            }


        })


    }
}