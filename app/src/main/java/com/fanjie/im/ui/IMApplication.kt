package com.fanjie.im.ui

import android.app.Application
import cn.bmob.v3.Bmob
import com.fanjie.im.BuildConfig
import com.hyphenate.chat.EMClient
import com.hyphenate.chat.EMOptions

/**
 * Created by shaofeng.wang on 2019/7/12.
 */
class IMApplication : Application() {

    companion object {
        lateinit var instance: IMApplication
    }


    override fun onCreate() {
        super.onCreate()

        instance = this
//        val options = EMOptions()
//        // 默认添加好友时，是不需要验证的，改成需要验证
//        options.setAcceptInvitationAlways(false);
//// 是否自动将消息附件上传到环信服务器，默认为True是使用环信服务器上传下载，如果设为 false，需要开发者自己处理附件消息的上传和下载
//        options.setAutoTransferMessageAttachments(true);
//// 是否自动下载附件类消息的缩略图等，默认为 true 这里和上边这个参数相关联
//        options.setAutoDownloadThumbnail(true);
//初始化
        EMClient.getInstance().init(applicationContext, EMOptions());
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(BuildConfig.DEBUG);


        Bmob.initialize(this, "72a0fcd642a696e982bc601e4f64f3f6")
    }
}