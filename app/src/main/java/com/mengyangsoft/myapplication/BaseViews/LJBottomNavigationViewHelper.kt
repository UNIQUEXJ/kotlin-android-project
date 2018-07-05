package com.mengyangsoft.myapplication.BaseViews

import android.annotation.SuppressLint
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView

class LJBottomNavigationViewHelper {
    companion object {
        @SuppressLint("RestrictedApi")
        open fun disableShiftMode(view: BottomNavigationView) {
            //获取子View BottomNavigationMenuView的对象
            val menuView = view.getChildAt(0) as BottomNavigationMenuView
            try {
                //设置私有成员变量mShiftingMode可以修改
                val shiftingMode = menuView.javaClass.getDeclaredField("mShiftingMode")
                shiftingMode.isAccessible = true
                shiftingMode.setBoolean(menuView, false)
                shiftingMode.isAccessible = false
                for (i in 0 until menuView.childCount) {
                    val item = menuView.getChildAt(i) as BottomNavigationItemView
                    //去除shift效果
                    item.setShiftingMode(false)
                    item.setChecked(item.itemData.isChecked)
                }
            } catch (e: NoSuchFieldException) {
            } catch (e: IllegalAccessException) {
            }

        }
    }
}
