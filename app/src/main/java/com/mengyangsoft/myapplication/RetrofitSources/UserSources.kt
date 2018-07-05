package com.mengyangsoft.myapplication.RetrofitSources

import com.mengyangsoft.myapplication.Models.BaseModel
import com.mengyangsoft.myapplication.Models.UserModel
import com.mengyangsoft.myapplication.RetrofitTools.NetServer
import retrofit2.Call
import retrofit2.Response

object UserSources {
    fun userInfo(option: Map<String,String>, success: (BaseModel<UserModel>?) -> Unit, fail: (Throwable?) -> Unit) {
        NetServer.retrofitServer.login(option).enqueue(object: retrofit2.Callback<BaseModel<UserModel>> {
            override fun onFailure(p0: Call<BaseModel<UserModel>>?, p1: Throwable?) {
                fail(p1)
            }

            override fun onResponse(p0: Call<BaseModel<UserModel>>?, p1: Response<BaseModel<UserModel>>?) {
                success(p1?.body())
            }

        })
    }

}