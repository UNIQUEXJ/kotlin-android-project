package com.mengyangsoft.myapplication.Models

data class BaseModel<T>(var success: Boolean = false, val errorCode: String = "", val msg: String = "", val data: T? = null)

data class BaseArrayModel<T>(var success: Boolean = false, val errorCode: String = "", val msg: String = "", val data: List<T>? = null)