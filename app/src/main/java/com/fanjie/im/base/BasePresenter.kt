package com.fanjie.im.base

import android.os.Handler
import android.os.Looper

/**
 * Created by shaofeng.wang on 2019/7/12.
 */
interface BasePresenter {

    companion object {
        val handler by lazy {
            Handler(Looper.getMainLooper())
        }
    }

    fun uiThread(f: () -> Unit) {
        handler.post { f() }
    }
}