package com.fanjie.im.contract

import com.fanjie.im.base.BasePresenter

/**
 * Created by shaofeng.wang on 2019/7/15.
 * ss
 */
interface DynamicContract  {

    interface presenter : BasePresenter {
        fun exitAPP()
    }

    interface Dynamicview {
      fun  onexutAppsuccess()
      fun  onexitAPPerror()

    }
}