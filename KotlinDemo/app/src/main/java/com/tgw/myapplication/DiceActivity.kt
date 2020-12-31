package com.tgw.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DiceActivity : AppCompatActivity() {
    lateinit var diceImage : ImageView
    lateinit var diceImage1 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dice)
        diceImage = findViewById(R.id.dice_image)
        diceImage1 = findViewById(R.id.dice_image1)

        val rollButton = findViewById<Button>(R.id.roll_button)
        rollButton.setOnClickListener{
            diceImage.setImageResource(getRandomDiceImage())
            diceImage1.setImageResource(getRandomDiceImage())
        }
    }

    private fun getRandomDiceImage() : Int {
        return when ((1..6).random()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}