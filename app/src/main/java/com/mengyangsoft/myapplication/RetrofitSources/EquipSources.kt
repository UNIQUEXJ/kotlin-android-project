package com.mengyangsoft.myapplication.RetrofitSources

import com.google.gson.Gson
import com.mengyangsoft.myapplication.RetrofitTools.NetServer
import retrofit2.Call
import retrofit2.Response
import com.mengyangsoft.myapplication.Datas.*
import com.mengyangsoft.myapplication.Models.*

object EquipSources {
    fun companyListWithEquipTypeList() {
        /*获取公司列表数据*/
        NetServer.retrofitServer.companyList(sid).enqueue(object: retrofit2.Callback<BaseArrayModel<CompanyModel>> {
            override fun onFailure(p0: Call<BaseArrayModel<CompanyModel>>?, p1: Throwable?) {
            }

            override fun onResponse(p0: Call<BaseArrayModel<CompanyModel>>?, p1: Response<BaseArrayModel<CompanyModel>>?) {
                if (p1?.body()?.success == true) {
                    companyList = p1?.body()?.data ?: ArrayList()
                }
            }
        })

        /*获取设备类型列表*/
        NetServer.retrofitServer.equipTypeList(sid).enqueue(object: retrofit2.Callback<BaseArrayModel<EquipTypeModel>> {
            override fun onFailure(p0: Call<BaseArrayModel<EquipTypeModel>>?, p1: Throwable?) {
            }

            override fun onResponse(p0: Call<BaseArrayModel<EquipTypeModel>>?, p1: Response<BaseArrayModel<EquipTypeModel>>?) {
                if (p1?.body()?.success == true) {
                    equipTypeList = p1?.body()?.data ?: ArrayList()
                }
            }
        })
    }

    fun equipList(equipReuest: EquipRequestModel, success: (EquipListTotalModel?) -> Unit, fail: (Int) -> Unit) {
        val json = Gson().toJson(equipReuest)
        NetServer.retrofitServer.equipList(equipReuest).enqueue(object: retrofit2.Callback<BaseModel<EquipListTotalModel>> {
            override fun onFailure(p0: Call<BaseModel<EquipListTotalModel>>?, p1: Throwable?) {
                fail(1)
            }

            override fun onResponse(p0: Call<BaseModel<EquipListTotalModel>>?, p1: Response<BaseModel<EquipListTotalModel>>?) {
                if (p1?.body()?.success == true) {
                    success(p1?.body()?.data)
                } else {
                    fail(1)
                }
            }
        })
    }
}