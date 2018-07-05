package com.mengyangsoft.myapplication.Extensions

import android.content.Context
import com.kaopiz.kprogresshud.KProgressHUD
import java.util.*

fun lj_hud(context: Context, label: String = ""): KProgressHUD {
    val hud = KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel(label).setDimAmount(0.3f).show()
    val task = object : TimerTask() {
        override fun run() {
            hud.dismiss()
        }
    }
    val timer = Timer()
    timer.schedule(task, 30000)
    return hud
}

fun lj_hudS(context: Context, label: String, delay: Long = 1000) {
    val hud = KProgressHUD.create(context).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setLabel(label).setDimAmount(0.3f).show()
    val task = object : TimerTask() {
        override fun run() {
            hud.dismiss()
        }
    }
    val timer = Timer()
    timer.schedule(task, delay)
}