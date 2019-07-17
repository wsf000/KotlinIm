package com.fanjie.im.ui

/**
 * Created by shaofeng.wang on 2019/7/12.
 */

    fun String.isValidUserName(): Boolean = this.matches(Regex("^[a-zA-Z]\\w{2,19}$"))
    fun String.isValidPassword(): Boolean = this.matches(Regex("^[0-9]{3,20}$"))
