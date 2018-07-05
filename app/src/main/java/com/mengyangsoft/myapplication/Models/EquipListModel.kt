package com.mengyangsoft.myapplication.Models

data class EquipListTotalModel(
        val total: Int = 0,
        val dataList: MutableList<EquipListModel>
)

data class EquipListModel(
        val id: String?,
        val code: String?,
        val name: String?,
        val equipmentTypeId: String?,
        val equipmentTypeName: String?,
        val companyId: String?,
        val companyName: String?,
        val isOnline: String?,
        val isRun: String?,
        val warnningCode: String?,
        val lastReceiveDate: String?,
        val attributeResList: List<EquipAttributeRes>
)


data class EquipAttributeRes(
        val id: String?,
        val code: String?,
        val name: String?,
        val value: String?
)

data class EquipRequestModel(
        var sid: String = "",
        var keyWords: String = "",
        var equipmentTypeId: String = "",
        var companyId: String = "",
        var isOnline: Int?,
        var pageSize: Int = 10,
        var pageNo: Int = 1
)