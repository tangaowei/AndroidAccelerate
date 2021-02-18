package com.tgw.arch.data

data class VisitorBean(
        val isSuccess: Boolean?,
        val message: String?,
        val errorCode: String?,
        val data: List<VisitorFloorBean>?
)

data class VisitorFloorBean(
        val title: String?,
        val floor: String?,
        val version: Int?,
        val extend: Int?,
        val data: List<VisitorFloorRoomBean>?
)

data class VisitorFloorRoomBean (
        val id: String?,
        val title: String?,
        val brief: String?,
        val url: String?,
        val thumb: String?
)