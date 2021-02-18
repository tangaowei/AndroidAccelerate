package com.tgw.arch.retrofit

import com.tgw.arch.data.VisitorBean
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    // 观展页 banner，运营接口
    @GET("api/biz/visitor")
    fun getVisitor(): Call<VisitorBean>
}