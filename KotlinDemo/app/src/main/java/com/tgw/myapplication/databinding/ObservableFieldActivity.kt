package com.tgw.myapplication.databinding

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableInt
import com.tgw.myapplication.R

class ObservableFieldActivity : AppCompatActivity() {
    private val observableFieldProfile = ObservableFieldProfile("Ada", "Lovelace", ObservableInt(0))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ObservableFieldProfileBinding =
            DataBindingUtil.setContentView(this, R.layout.observable_field_profile)
        binding.user = observableFieldProfile
    }

    fun onLike(view: View) {
        observableFieldProfile.likes.set(observableFieldProfile.likes.get() + 1)
    }
}