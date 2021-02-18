package com.tgw.arch.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tgw.arch.data.VisitorBean
import com.tgw.arch.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ExhibitViewModel : ViewModel() {
    var visitorBean : MutableLiveData<VisitorBean> = MutableLiveData()
    fun requestVisitorRetrofit(): MutableLiveData<VisitorBean> {
        RetrofitClient().apiService().getVisitor().enqueue(
            object : Callback<VisitorBean> {
                override fun onFailure(call: Call<VisitorBean>, t: Throwable) {
                    Log.d("TGW", "----errorMsg: {$call}")
                }

                override fun onResponse(call: Call<VisitorBean>, response: Response<VisitorBean>) {
                    Log.d("TGW", "----response.body(): {${response.body()}}")
                    visitorBean.value = response.body()
                }

            }
        )
        return visitorBean
    }
}