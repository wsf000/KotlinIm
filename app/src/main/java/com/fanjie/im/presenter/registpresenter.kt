package com.fanjie.im.presenter

import cn.bmob.v3.BmobUser
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.SaveListener
import com.fanjie.im.contract.RegistContractr
import com.fanjie.im.ui.isValidPassword
import com.fanjie.im.ui.isValidUserName
import com.hyphenate.chat.EMClient
import com.hyphenate.exceptions.HyphenateException
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


/**
 * Created by shaofeng.wang on 2019/7/15.
 */
class registpresenter(val view: RegistContractr.registview) : RegistContractr.presenter {

    override fun register(usrename: String, password: String, configpassword: String) {

        //开始核对
        if (usrename.isValidUserName()) {
            if (password.isValidPassword()) {
                if (password.equals(configpassword)) {
                    view.onStartRegist()

//注册到bom云服务
                    registerBom(usrename, password)


                } else view.onConfigPassword()


            } else view.onPasswordError()

        } else view.onUsernameError()
    }

    private fun registerBom(usrename: String, password: String) {


        val bu = BmobUser()
        bu.username = usrename
        bu.setPassword(password)
        bu.signUp<BmobUser>(object : SaveListener<BmobUser>() {
            override fun done(s: BmobUser ?, e: BmobException?) {
                if (e == null) {
//                    toast("添加数据成功，返回objectId为：" + objectId)
                    //注册成功,然后在注册到环信

                    registIM(usrename, password)

                } else {
                    if (e.errorCode == 202) view.onHaveUser()
                    else
                        view.onRegisterror()
                }
            }
        })
    }

    private fun registIM(usrename: String, password: String) {

        doAsync {
            try {
                EMClient.getInstance().createAccount(usrename, password)

                uiThread {
                    view.onRegistsuccess()
                }
            } catch (e: HyphenateException) {

                uiThread {
                    view.onRegisterror()
                }

            }


        }

    }
}