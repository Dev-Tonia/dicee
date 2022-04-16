package com.example.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn: Button = findViewById(R.id.btnRoll)


        val startBtn: Button = findViewById(R.id.startBtn)
         val card: CardView = findViewById(R.id.card)
        val restart: Button = findViewById(R.id.restart)

        restart.visibility = View.GONE
        btn.setOnClickListener {
            btnroll()

        }
        startBtn.setOnClickListener {
            card.visibility = View.GONE

        }


    }

    private fun btnroll() {

        val dice = Dice(6)
        val diceNum1 = dice.diceRoll()
        val diceNum2 = dice.diceRoll()

        val diceImg1: ImageView = findViewById(R.id.diceImg1)
        val diceImg2: ImageView = findViewById(R.id.diceImg2)
        val scoreKeeper: TextView = findViewById(R.id.score)
        val scoreKeeperValue = scoreKeeper.text.toString()
        val rollsRemain: TextView = findViewById(R.id.rollsRemain)
        val restart: Button = findViewById(R.id.restart)
        val rollCountValue = rollsRemain.text.toString()
        val btn: Button = findViewById(R.id.btnRoll)

        var score = scoreKeeperValue.toInt()
        var numberOfRoll = rollCountValue.toInt()
        var point = diceNum1 + diceNum2
        point += score
        var rolls = --numberOfRoll

//        getting the value of the dice depending on the random number generated.
        fun  imageResult(imgId: Int): Int {
           return when (imgId) {
                1 -> R.drawable.dice_1
                2 -> R.drawable.dice_2
                3 -> R.drawable.dice_3
                4 -> R.drawable.dice_4
                5 -> R.drawable.dice_5
                else -> R.drawable.dice_6
            }
        }


//        setting the value to the screen.
        diceImg1.setImageResource(imageResult(diceNum1))
        diceImg2.setImageResource(imageResult(diceNum2))
        rollsRemain.text = "$rolls"
        scoreKeeper.text = "$point"
//        checking the condition declaring a winner
        if (numberOfRoll == 0){
             restart.visibility = View.VISIBLE
            btn.visibility = View.GONE
            if (point == 100){
                val toast = Toast.makeText(this, "YOU WIN", Toast.LENGTH_LONG)
                toast.show()

            }else{
                val toast = Toast.makeText(this, "YOU LOOSE", Toast.LENGTH_LONG)
                toast.show()

            }
            restart.setOnClickListener {
                point = 0
                scoreKeeper.text = "$point"
                rollsRemain.text = "12"
                btn.visibility = View.VISIBLE
                restart.visibility = View.GONE
            }

        }

    }
}

class Dice(private val sides: Int){

    fun diceRoll(): Int{
       return (1..sides).random()
    }
}

