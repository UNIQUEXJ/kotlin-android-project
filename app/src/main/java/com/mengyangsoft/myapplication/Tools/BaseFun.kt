package com.mengyangsoft.myapplication.Tools

import android.content.Context
import android.widget.Toast

fun lj_toast(context: Context, label: String) {
    Toast.makeText(context, label, Toast.LENGTH_SHORT).show()
}