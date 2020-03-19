package com.porraz0.udemyfirstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //Read value from Intent
        val intent = intent
        val received : String = intent.getStringExtra("Name")

        val tmpText = lblDemo.text.toString()
        lblDemo.text =  "$tmpText $received"

    }



    fun ReturnHome(view: View){
        val intent = Intent(applicationContext, MainActivity::class.java)

        startActivity(intent)
    }
}
