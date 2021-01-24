package com.tgw.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.tgw.myapplication.databinding.ObservableFieldActivity


class MainActivity : AppCompatActivity() {

    @SuppressLint("JavascriptInterface")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rollButton = findViewById<Button>(R.id.roll_button)
        rollButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, DiceActivity::class.java))
        }

        val aboutMeButton = findViewById<Button>(R.id.aboutme_button)
        aboutMeButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, AboutMeActivity::class.java))
        }

        val constraintButton = findViewById<Button>(R.id.constraint_button)
        constraintButton.setOnClickListener{
            startActivity(Intent(this@MainActivity, ConstraintLayoutActivity::class.java))
        }

        val observableButton = findViewById<Button>(R.id.observable_fields)
        observableButton.setOnClickListener {
            startActivity(Intent(this@MainActivity, ObservableFieldActivity::class.java))
        }
    }
}