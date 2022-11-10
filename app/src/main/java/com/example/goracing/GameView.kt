package com.example.goracing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.View

class GameView(var c :Context, var gameTask :GameTask):View(c)
{
    private var myPaint: Paint? = null
    private var speed = 1
    private var time = 0
    private var score = 0
    private var myCarPosition = 0
    private var otherCars = ArrayList<HashMap<String,Any>>()

    var viewWidth = 0
    var viewHeight = 0
    init {
        myPaint = Paint()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        viewWidth = this.measuredWidth
        viewHeight = this.measuredHeight

        if (time % 700 < 10 +speed){
            val map = HashMap<String,Any>()
            map["lane"] = (0..2).random()
            map["startTime"] = time
            otherCars.add(map)

        }
        time = time + 10 + speed
        val carWidth = viewWidth / 5
        var carHeight = carWidth + 10
        myPaint!!.style = Paint.Style.FILL
        val d = resources.getDrawable(R.drawable.cars_reds, null)
        d.setBounds(
            myCarPosition * viewWidth / 3 + viewWidth / 15 +25,
            viewHeight-2 - carHeight,
            myCarPosition * viewWidth / 3 + viewWidth / 15 + carWidth - 25 ,
            viewHeight - 2
        )
        d.draw(canvas!!)
        myPaint!!.color = Color.GREEN
        var highScore = 0

        for (i in otherCars.indices){
            try{
                val carX = otherCars[i]["lane"] as Int * viewWidth / 3 + viewWidth / 15
                var carY = time - otherCars[i]["startTime"] as Int
                val d2 = resources.getDrawable(R.drawable.cars_yellows,null)

                d2.setBunds(
                    carX + 25 , carY - carHeight , carX + carWidth - 25 , carY
                )
                d2.draw(canvas)

            }
            catch
                (e:Exception){
                    e.printStackTrace()
            }
        }

    }

}