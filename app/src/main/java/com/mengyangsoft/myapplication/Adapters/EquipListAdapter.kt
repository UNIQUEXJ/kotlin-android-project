package com.mengyangsoft.myapplication.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import com.mengyangsoft.myapplication.Models.EquipListModel
import com.mengyangsoft.myapplication.R
import com.mengyangsoft.myapplication.items.EquipListHoller

class EquipListAdapter(ds: List<EquipListModel>, context: Context) : BaseAdapter() {

    private var equipList: List<EquipListModel> = ArrayList()
    private var inflater: LayoutInflater? = null
    lateinit var clickItem: (Int) -> Unit

    init {
        equipList = ds
        inflater = LayoutInflater.from(context)
    }

    fun updateData(ds: List<EquipListModel>, listView: ListView) {
        equipList = ds
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var equipListHoller: EquipListHoller
        if (convertView == null) {
            convertView = inflater?.inflate(R.layout.item_equiplist, null)
            equipListHoller = EquipListHoller(convertView!!)
            convertView.tag = equipListHoller
        } else {
            equipListHoller = convertView.tag as EquipListHoller
        }
        equipListHoller.setValue(equipList[position], position)
        equipListHoller.clickItem = {index ->
            clickItem(index)
        }
        return convertView!!
    }

    override fun getItem(position: Int): Any {
        return equipList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return equipList.size
    }

}