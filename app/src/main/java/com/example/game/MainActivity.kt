package com.example.game

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.milliseconds

val StepMas = arrayOf(
    "Камень",
    "Бумага",
    "Ножницы",
    "Ящерица",
    "Спок"
)

val resultarray: Array<Array<String>> = arrayOf(
    arrayOf("Ничья","Бумага заворачивает Камень","Камень разбивает Ножницы","Камень давит Ящерицу","Спок испаряет Камень"),
    arrayOf("Бумага заворачивает Камень","Ничья","Ножницы режут Бумагу","Ящерица ест Бумагу","Бумага подставляет Спока"),
    arrayOf("Камень разбивает Ножницы","Ножницы режут Бумагу","Ничья","Ножницы отрезают голову Ящерице","Спок ломает Ножницы"),
    arrayOf("Камень давит Ящерицу","Ящерица ест Бумагу","Ножницы отрезают голову Ящерице","Ничья","Ящерица травит Спока"),
    arrayOf("Спок испаряет Камень","Бумага подставляет Спока","Спок ломает Ножницы","Ящерица травит Спока","Ничья")
)

val resultgame = arrayOf(
    intArrayOf(3,0,1,1,0),
    intArrayOf(1,3,0,0,1),
    intArrayOf(0,1,3,1,0),
    intArrayOf(0,1,0,3,1),
    intArrayOf(1,0,1,0,3)
)
fun findIndex(arr: Array<String>, item: String): Int {
    return arr.indexOf(item)
}


class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val Rock = findViewById<Button>(R.id.btnRock)//камень
        val Paper = findViewById<Button>(R.id.btnPaper)//бумага
        val Lizard = findViewById<Button>(R.id.btnLizard)//ящерица
        val Spock = findViewById<Button>(R.id.btnSpock)//спок
        val Scissors = findViewById<Button>(R.id.btnScissors)//ножницы
        val btnMain = findViewById<Button>(R.id.btnStart)

        val StepUser = findViewById<TextView>(R.id.txtRoot)
        val StepComp = findViewById<TextView>(R.id.txtComp)
        val Result = findViewById<TextView>(R.id.txtResult)

        val WinComp = findViewById<TextView>(R.id.txtCompWin)
        val WinUser = findViewById<TextView>(R.id.txtUserWin)
        val Zero = findViewById<TextView>(R.id.txtZero)
        val btnStat = findViewById<Button>(R.id.btnStat)
        val time = findViewById<TextView>(R.id.txtTime)

        val panel1 = findViewById<LinearLayout>(R.id.Layout1)
        val panel2 = findViewById<LinearLayout>(R.id.Layout2)
        val panel3 = findViewById<LinearLayout>(R.id.Layout3)

        var begin:Double = 0.0
        begin = System.currentTimeMillis().toDouble()
        Rock.setOnClickListener(){
            StepUser.text = Rock.text.toString()
            Reset()
        }
        Paper.setOnClickListener(){
            StepUser.text = Paper.text.toString()
            Reset()
        }
        Lizard.setOnClickListener(){
            StepUser.text = Lizard.text.toString()
            Reset()
        }
        Spock.setOnClickListener(){
            StepUser.text = Spock.text.toString()
            Reset()
        }
        Scissors.setOnClickListener(){
            StepUser.text = Scissors.text.toString()
            Reset()
        }

        btnMain.setOnClickListener(){
            btnMain.isEnabled = false
            StepComp.text = StepMas.random().toString()
            val dopuser = findIndex(StepMas, StepUser.text.toString())
            val dopcomp = findIndex(StepMas, StepComp.text.toString())

            Result.text = resultarray[dopcomp][dopuser]
            val dopcolor = resultgame[dopcomp][dopuser]
            val ttime = ((((System.currentTimeMillis().toDouble()- begin)/1000.0)*100).roundToInt().toDouble()/100)
            time.text = "$ttime сек"
            if (dopcolor == 1){
                Result.setBackgroundColor(Color.argb(255,252,45,45))
                WinComp.text = (WinComp.text.toString().toInt() + 1).toString()
            }
            if (dopcolor == 0){
                Result.setBackgroundColor(Color.argb(255,97,252,45))
                WinUser.text = (WinUser.text.toString().toInt() + 1).toString()
            }
            if (dopcolor == 3){
                Result.setBackgroundColor(Color.argb(255,242,215,247))
                Zero.text = (Zero.text.toString().toInt() + 1).toString()
            }

            begin = System.currentTimeMillis().toDouble()
        }
        btnStat.setOnClickListener(){
            panel1.visibility = if (panel1.visibility  == View.VISIBLE) View.INVISIBLE else View.VISIBLE
            panel2.visibility = if (panel2.visibility  == View.VISIBLE) View.INVISIBLE else View.VISIBLE
            panel3.visibility = if (panel3.visibility  == View.VISIBLE) View.INVISIBLE else View.VISIBLE
        }
    }

    private fun Reset() {
        val StepComp = findViewById<TextView>(R.id.txtComp)
        val Result = findViewById<TextView>(R.id.txtResult)
        val btnMain = findViewById<Button>(R.id.btnStart)
        StepComp.text ="ХОД КОМПЬЮТЕРА!"
        Result.text = "Результат"
        Result.setBackgroundColor(Color.argb(255,242,215,247))
        btnMain.isEnabled = true
    }
}