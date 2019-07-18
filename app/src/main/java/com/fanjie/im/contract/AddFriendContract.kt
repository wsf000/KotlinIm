package com.fanjie.im.contract

import com.fanjie.im.base.BasePresenter
import java.nio.channels.spi.AbstractSelectionKey

/**
 * Created by shaofeng.wang on 2019/7/18.
 * 添加朋友的mvp协议类
 */
interface AddFriendContract  {

    interface AddFriendPresenter :BasePresenter {

        fun  SearchFridend(key: String)

    }

    interface AddFriendView {

        fun SearchSuccess()
        fun SearchFiled()

    }

}