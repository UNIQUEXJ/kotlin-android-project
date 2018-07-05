package com.mengyangsoft.myapplication.Fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.mengyangsoft.myapplication.Adapters.EquipListAdapter
import com.mengyangsoft.myapplication.BaseViews.LJNavigationBar
import com.mengyangsoft.myapplication.Datas.companyList
import com.mengyangsoft.myapplication.Datas.sid
import com.mengyangsoft.myapplication.Extensions.lj_hud
import com.mengyangsoft.myapplication.MainActivity
import com.mengyangsoft.myapplication.Models.EquipListModel
import com.mengyangsoft.myapplication.Models.EquipListTotalModel
import com.mengyangsoft.myapplication.Models.EquipRequestModel

import com.mengyangsoft.myapplication.R
import com.mengyangsoft.myapplication.RetrofitSources.EquipSources
import kotlinx.android.synthetic.main.fragment_equip_list.*
import org.jetbrains.anko.singleLine

class EquipListFragment : Fragment() {

    private var keyWords: String = ""
    private var pageNo: Int = 1
    private var pageSize: Int = 20
    private var equipListData: MutableList<EquipListModel> = ArrayList()
    private var adapter: EquipListAdapter? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_equip_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewAneEvents()
        EquipSources.companyListWithEquipTypeList()
        getEquipList(false)
    }

    private fun initViewAneEvents() {
        naviation_bar.setLeftImageResource(R.mipmap.icon_account)
        naviation_bar.setLeftClickListener(View.OnClickListener {
            goLogin()
        })
        naviation_bar.setTitle(R.string.equip_list)
        naviation_bar.setTitleColor(Color.WHITE)

        naviation_bar.addAction(object : LJNavigationBar.TextAction("筛选") {
            override fun performAction(view: View) {
                println(companyList.count())
            }
        })
        naviation_bar.setActionTextColor(Color.WHITE)

        search_editText.singleLine = true

        search_editText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                keyWords = s.toString()
            }

        })
        equip_list_view.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            println(equipListData[position].name)
        }
        adapter = EquipListAdapter(equipListData, this.context)
        equip_list_view.adapter = adapter

//        adapter?.clickItem = {index ->
//            println(index)
//        }

//        长按删除
//        equip_list_view.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, position, _ ->
//            val builder = AlertDialog.Builder(this.context)
//            builder.setMessage("delete?")
//            builder.setTitle("tip")
//            builder.setPositiveButton("ok", { _, _ ->
//                equipListData.removeAt(position)
//                adapter?.updateData(equipListData, equip_list_view)
//            })
//            builder.setNegativeButton("cancel", {_, _ ->})
//            builder.create().show()
//            println(equipListData[position].name)
//            true
//        }
    }

    private fun goLogin() {
        val intent = Intent()
        intent.setClass(this.activity, MainActivity::class.java)
        startActivity(intent)
        this.activity.finish()
    }

    private fun getEquipList(isMore: Boolean) {
        val hud = lj_hud(this.activity, "加载中...")
        val requestModel = EquipRequestModel(sid = sid, keyWords = keyWords, equipmentTypeId = "", companyId = "", isOnline = 1, pageSize = pageSize, pageNo = pageNo)
        EquipSources.equipList(requestModel, success = { models ->
            hud.dismiss()
            setListData(isMore, models)
        }, fail = {
            hud.dismiss()
        })
    }

    private fun setListData(isMore: Boolean, models: EquipListTotalModel?) {
        if (isMore) {

        } else {
            equipListData = models?.dataList ?: ArrayList()
            adapter?.updateData(equipListData, equip_list_view)
        }
    }

}// Required empty public constructor
