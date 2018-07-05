package com.mengyangsoft.myapplication

import android.content.Intent
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.view.View
import com.mengyangsoft.myapplication.BaseViews.LJTabbarActivity
import com.mengyangsoft.myapplication.Datas.sid
import com.mengyangsoft.myapplication.Datas.usermodel
import com.mengyangsoft.myapplication.Events.Event
import com.mengyangsoft.myapplication.Extensions.lj_hud
import com.mengyangsoft.myapplication.RetrofitSources.UserSources
import com.mengyangsoft.myapplication.Tools.lj_toast
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.singleLine

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        EventBus.getDefault().register(this)

        username.singleLine = true
        password.singleLine = true

        password.transformationMethod = PasswordTransformationMethod.getInstance()

        loginBtn.onClick {
            loginClick()
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }

    fun loginClick() {
        val hud = lj_hud(this, "登录中...")
        val para = mapOf("username" to username.text.toString(), "password" to password.text.toString())
        UserSources.userInfo(para, success = { model ->
            hud.dismiss()
            Thread(Runnable { EventBus.getDefault().post(Event(model?.msg ?: "")) }).start()
            if (model?.success == true) {
                usermodel = model?.data
                sid = model?.data?.sid ?: ""
                loginSuccess()
            }
        }, fail= {
            hud.dismiss()
            Thread(Runnable { EventBus.getDefault().post(Event( "登录失败")) }).start()
        })
    }

    fun loginSuccess() {
        val intent = Intent()
        intent.setClass(this, LJTabbarActivity::class.java)
        startActivity(intent)
        finish()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: Event) {
        val msg = event.messgae
        lj_toast(this, msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }
}
