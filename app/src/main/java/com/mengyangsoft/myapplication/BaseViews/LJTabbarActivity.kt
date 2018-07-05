package com.mengyangsoft.myapplication.BaseViews

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import com.mengyangsoft.myapplication.Fragments.EquipListFragment
import com.mengyangsoft.myapplication.Fragments.WarnFragment
import com.mengyangsoft.myapplication.R
import kotlinx.android.synthetic.main.activity_ljtabbar.*

class LJTabbarActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private var equipFragment: Fragment? = null
    private var warnFragment: Fragment? = null
    private var menuItem: MenuItem? = null
    private val fragmentList = ArrayList<Fragment>()
    private var adapter: TabbarAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ljtabbar)
        initView()

        tabbar.setOnNavigationItemSelectedListener(this)
        LJBottomNavigationViewHelper.disableShiftMode(tabbar)

//        addBadge()
//        修改状态
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }

    //    添加Fragment
    private fun initView() {
        equipFragment = EquipListFragment()
        warnFragment = WarnFragment()
        fragmentList.add(equipFragment!!)
        fragmentList.add(warnFragment!!)

        adapter = TabbarAdapter(supportFragmentManager, fragmentList)
        view_page.adapter = adapter
        view_page.offscreenPageLimit = fragmentList.size
        view_page.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (menuItem != null) {
                    menuItem!!.isChecked = false
                } else {
                    tabbar.menu.getItem(0).isChecked = false
                }
                menuItem = tabbar.menu.getItem(position)
                menuItem!!.isChecked = true
            }

        })
    }

    //    添加角标
    private fun addBadge() {
        val menuView = tabbar.getChildAt(0) as BottomNavigationMenuView
        val tab = menuView.getChildAt(1)
        val itemView = tab as BottomNavigationItemView
        val badge = LayoutInflater.from(this).inflate(R.layout.tabbar_badge, menuView, false)
        itemView.addView(badge)
        val count = badge.findViewById<TextView>(R.id.tv_msg_count) as TextView
        count.text = 2.toString()
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        var id = 0
        when (menuItem.itemId) {
            R.id.i_equip -> id = 0
            R.id.i_warn -> id = 1
            else -> return false
        }
        view_page.setCurrentItem(id, false)
        return true
    }

    internal inner class TabbarAdapter(fm: FragmentManager, private val fragmentList: List<Fragment>) : FragmentPagerAdapter(fm) {
        override fun getItem(position: Int): Fragment {
            return fragmentList[position]
        }

        override fun getCount(): Int {
            return fragmentList.size
        }

    }
}
