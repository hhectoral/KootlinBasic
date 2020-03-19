package com.porraz0.udemyfirstapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import kotlinx.android.synthetic.main.activity_main_runanables.*

class MainRunanablesActivity : AppCompatActivity() {

    var number:Float = 0F
    var handler : Handler = Handler()
    var runnable: Runnable = Runnable {  }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_runanables)


    }

    fun Start(view: View){
        number = -0.9F
        lblRunnable.text = "0.0"

            runnable = object : Runnable{
            override fun run() {
                number += 0.1F
                lblRunnable.text = "%.1f".format(number)

                handler.postDelayed(this, 100)
            }

        }

        handler.post (runnable)

    }

    fun Stop (view: View){


        handler.removeCallbacks(runnable)
        number =0F
        lblRunnable.text = "0.0"
    }
}
