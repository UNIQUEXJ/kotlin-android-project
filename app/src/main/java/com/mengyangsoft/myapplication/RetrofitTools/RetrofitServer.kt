package com.mengyangsoft.myapplication.RetrofitTools

import com.mengyangsoft.myapplication.Models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RetrofitServer {
    @POST("commonapi/login")
    fun login(@QueryMap option:Map<String, String>): Call<BaseModel<UserModel>>

    @POST("commonapi/getEquipmentListByPage")
    fun equipList(@Body equipRequestModel: EquipRequestModel): Call<BaseModel<EquipListTotalModel>>

    @POST("commonapi/getCompanyList")
    fun companyList(@Query("sid") sid: String): Call<BaseArrayModel<CompanyModel>>

    @POST("commonapi/getEquipmentTypeListByCompany")
    fun equipTypeList(@Query("sid") sid: String): Call<BaseArrayModel<EquipTypeModel>>
}

object NetServer {
    //单例模式
    val retrofitServer: RetrofitServer by lazy {
        Retrofit.Builder().baseUrl("http://112.80.94.180:8080/a/api/").addConverterFactory(GsonConverterFactory.create())
                .build().create(RetrofitServer::class.java)
    }
}