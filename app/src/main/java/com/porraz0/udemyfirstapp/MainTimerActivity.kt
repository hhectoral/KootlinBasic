package com.porraz0.udemyfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main_timer.*

class MainTimerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_timer)

        object : CountDownTimer(10000,1000){
            override fun onFinish() {

                Toast.makeText(applicationContext,"It's over", Toast.LENGTH_LONG).show()
                lblTimer2.text = "Its Finished"
                lblTimer.text = "0"

            }

            override fun onTick(p0: Long) {
                lblTimer.text = (p0 / 1000).toString()


            }

        }.start()
    }
}
