package com.tgw.arch.retrofit

import com.tgw.arch.okhttp.BaseUrls
import com.tgw.arch.okhttp.OkClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private var mRetrofit : Retrofit =  provideRetrofit(BaseUrls.getHost())

        private fun provideRetrofit(baseUrl: String) : Retrofit {
            return Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(OkClient.getInstance().client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
    }

    fun apiService(): ApiService = mRetrofit.create(ApiService::class.java)
}