package com.porraz0.udemyfirstapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_currency.*
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class CurrencyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)


    }

    inner class Download:AsyncTask<String,Void,String>(){
        override fun doInBackground(vararg params: String?): String {

            var result = ""
            var url : URL
            val httpUrlConnection : HttpURLConnection

            try {
                url = URL(params[0])
                httpUrlConnection = url.openConnection() as HttpURLConnection

                val inputStream = httpUrlConnection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)

                var data = inputStreamReader.read()

                while (data > 0){
                    val character = data.toChar()
                    result += character
                    data = inputStreamReader.read()
                }

            }catch (e:Exception){
                e.printStackTrace()
            }


            return result
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)

            try {

                val jsonObject = JSONObject(result)
                println(jsonObject)
                var base = jsonObject.getString("base")
                println(base)

                val date = jsonObject.getString("date")
                println(date)

                val rates= jsonObject.getString("rates")
                println(rates)

                var newJsonObject = JSONObject(rates)
                val chf = newJsonObject.getString("CHF")
                println(chf)
                txtCHF.setText(chf)

                val czk = newJsonObject.getString("CZK")
                println(czk)
                txtCZK.setText(czk)

                val tl = newJsonObject.getString("TRY")
                println(tl)
                txtTRY.setText(tl)

            }catch (e:Exception){e.printStackTrace()}

        }

    }

    fun getCurrency(view: View) {

        var downloadData = Download()

        try {
            val url = "http://data.fixer.io/api/latest"
            val chosenBase = "&base="+txtCurrency.text.toString()
            val apiKey = "?access_key=e8ad5afd846d6a458ec847b186019e46"
            downloadData.execute("$url$apiKey$chosenBase&format=1")

        }catch (e:Exception){e.printStackTrace()}

    }
}
