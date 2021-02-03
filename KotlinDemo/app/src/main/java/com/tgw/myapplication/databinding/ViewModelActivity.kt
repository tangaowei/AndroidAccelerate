package com.tgw.myapplication.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.tgw.myapplication.R


class ViewModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProviders.of(this).get(ProfileLiveDataViewModel::class.java)

        val binding: ViewmodelProfileBinding = DataBindingUtil.setContentView(this, R.layout.viewmodel_profile)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this
    }
}