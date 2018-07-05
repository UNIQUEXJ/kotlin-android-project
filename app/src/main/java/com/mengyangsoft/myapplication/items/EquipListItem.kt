package com.mengyangsoft.myapplication.items

import android.graphics.drawable.AnimationDrawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.mengyangsoft.myapplication.Models.EquipListModel
import com.mengyangsoft.myapplication.R
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.imageResource
import org.jetbrains.anko.textColor

class EquipListHoller(view: View) {
    private var img: ImageView = view.findViewById(R.id.item_equiplist_img)
    private var name: TextView = view.findViewById(R.id.item_equiplist_name)
    private var statusTv: TextView = view.findViewById(R.id.item_equiplist_status_text)
    private var statusV: View = view.findViewById(R.id.item_equiplist_status)
    private var styleTv: TextView = view.findViewById(R.id.item_equiplist_style)
    private var typeTv: TextView = view.findViewById(R.id.item_equiplist_type)
    private var pressureTv: TextView = view.findViewById(R.id.item_equiplist_pressure)
    private var powerTv: TextView = view.findViewById(R.id.item_equiplist_power)
    private var dateTv: TextView = view.findViewById(R.id.item_equiplist_date)
    lateinit var clickItem: (Int) -> Unit

    fun setValue(dataItem: EquipListModel, position: Int) {

        if ( (dataItem.isRun ?: "") != "0" && (dataItem.isOnline ?: "") != "0") {
            img.alpha = 1.0f
            img.imageResource = R.drawable.imgs_animate
            val animateDra = img.drawable as AnimationDrawable
            animateDra.start()
        } else if ((dataItem.isOnline ?: "") != "0") {
            img.imageResource = R.mipmap.icon_device
            img.alpha = 1.0f
        } else {
            img.imageResource = R.mipmap.icon_device
            img.alpha = 0.5f
        }
        if ((dataItem.isOnline ?: "") == "0") {
            statusV.backgroundResource = R.drawable.equip_status_unline
            statusTv.text = "离线"
            statusTv.textColor = R.color.colorUnline
        } else if ((dataItem.warnningCode ?: "") != "0" && (dataItem.warnningCode ?: "") != "") {
            statusV.backgroundResource = R.drawable.equip_status_warn
            statusTv.text = "报警"
            statusTv.textColor = R.color.colorWarn
        } else if ((dataItem.isRun ?: "") == "0") {
            statusV.backgroundResource = R.drawable.equip_status_stop
            statusTv.text = "停机"
            statusTv.textColor = R.color.colorStop
        } else {
            statusV.backgroundResource = R.drawable.equip_status_run
            statusTv.text = "运行"
            statusTv.textColor = R.color.colorRun
        }

        name.text = dataItem.name
        styleTv.text = dataItem.companyName
        typeTv.text = dataItem.equipmentTypeName
        dateTv.text = "最后接收数据时间:" + dataItem.lastReceiveDate
        val pressures = dataItem.attributeResList.filter { it.name == "功率" }
        if (pressures.isNotEmpty()) {
            pressureTv.text = if ((pressures[0].value ?: "") == "") {
                "功率未知"
            } else {
                "功率: " + pressures[0].value
            }
        }

        val powers = dataItem.attributeResList.filter { it.name == "电压" }
        if (powers.isNotEmpty()) {
            powerTv.text = if ((powers[0].value ?: "") == "") {
                "电压未知"
            } else {
                "电压: " + powers[0].value
            }
        }

//        name.onClick {
//            clickItem(position)
//        }
    }
}