package com.porraz0.udemyfirstapp

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.porraz0.udemyfirstapp.SimpsonClass.Simpson
import com.porraz0.udemyfirstapp.SimpsonClass.Tompson
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Values in sharedPreferences
        val sharedPreferences = this.getSharedPreferences("com.porraz0.udemyfirstapp", Context.MODE_PRIVATE)
        //sharedPreferences.edit().putString("Name", "Hector Alonso Hernandez Aleman").apply()
        lblDemo.text = sharedPreferences.getString("Name", "No name assigned") + " SDK: " + android.os.Build.VERSION.SDK_INT

        //Create Data Base
        try {

            val myDB = this.openOrCreateDatabase("Music", Context.MODE_PRIVATE, null)

            myDB.execSQL("CREATE TABLE IF NOT EXISTS musicians (name VARCHAR, age INT(2))")
            myDB.execSQL("INSERT INTO musicians VALUES('Dave Mustaine', 51)")

            val cursor = myDB.rawQuery("SELECT * FROM musicians", null)
            val nameIndex = cursor.getColumnIndex("name")
            val ageIndex = cursor.getColumnIndex("age")

            cursor.moveToFirst()

            while (cursor != null){
                cursor.getString(nameIndex)
                cursor.getInt(ageIndex)

                cursor.moveToNext()
            }

            cursor?.close()

        }catch (e:Exception){
            e.printStackTrace()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_values, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item?.itemId == R.id.itmAdd){

            Toast.makeText(applicationContext,"Diste clic en el menu", Toast.LENGTH_LONG).show()

            //get Access to Pictures Library
            //Go to manifest to get access

            if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),2)
            }else{
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 1)

            }
        }


        return super.onOptionsItemSelected(item)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if(requestCode == 2){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent, 1)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode != 1 && resultCode != Activity.RESULT_OK || data == null) {
            Toast.makeText(applicationContext, "El formato no es compatibble", Toast.LENGTH_LONG)
            return
        }
            val image = data.data

            try {
                val selImg = if(android.os.Build.VERSION.SDK_INT >= 29){
                    ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, image!!))
                } else{
                    MediaStore.Images.Media.getBitmap(this.contentResolver, image)
                }

                imgUser.setImageBitmap(selImg)

            }catch (e:Exception){
                e.printStackTrace()
            }


        super.onActivityResult(requestCode, resultCode, data)
    }

    fun sumVals(a: Int, b: Int): Int {
        return a + b
    }

    fun demoBasic() {

        //Constants
        val x = 5
        val y = 4

        //Define Type of Variable
        var myInt: Int
        var myString: String

        //Variables
        var resp = x + y

        myInt = 56
        myString = "Esto se va a descontrolar"
        println(resp)
        println(myInt)
        println(myString)


        //Arrays
        val myArray = arrayOfNulls<String>(10)
        myArray[0] = myString
        println(myArray[0])

        val myIntegerArray = intArrayOf(1, 2, 3, 4, 5)
        println(myIntegerArray.size)
        myIntegerArray[0] = 22
        println(myIntegerArray[0])

        //List
        val myList = ArrayList<String>()
        myList.add("contador 1")
        myList.add("Contador 2")

        println(myList)

        myList.add(1, "Contador Insertado")
        println(myList)

        //Set
        val mySet = HashSet<String>()

        mySet.add("Texto1")
        mySet.add("Texto1")

        println(mySet)

        //Map
        val myMap = HashMap<String, String>()

        myMap.put("Lead", "James")
        myMap.put("Guitar", "Kirk")

        println(myMap)
        println(myMap["Lead"])

        //Operators
        /*
        ++
        --
        <
        >
        >=
        <=
        ==
        !=
        &&
        ||
         */

        //Switch
        separator("Switch")
        val day = 1
        var strDay = ""

        when (day) {
            1 -> strDay = "Monday"
            2 -> strDay = "Tuesday"
            3 -> strDay = "Wednesday"
            4 -> strDay = "Thursday"
            5 -> strDay = "Friday"
            6 -> strDay = "Saturday"
            7 -> strDay = "Sunday"
            else -> strDay = "No day"

        }
        println(strDay)

        //For
        separator("For")
        val forArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val q = forArray[0] / 3 * 5
        println(q)

        for (number in forArray) {
            println(number)
        }

        for (i in forArray.indices) {
            println(forArray[i])
        }

        for (i in 0..9) {
            println(i)
        }

        //while
        separator("While")
        var i = 0
        while (i < 10) {
            println(i++)
        }
    }

    fun separator(Title: String) {
        println("*****************************************************************")
        println(Title)
        println("*****************************************************************")

    }


    fun makeSimpsons(view: View){

//        if(txtAge.text.toString() == ""){
//
//            return
//        }

        val name = txtName.text.toString()

        //val age = Integer.parseInt(txtAge.text.toString())
        val age = txtAge.text.toString().toIntOrNull()

        val job = txtJob.text.toString()

        //val homer = Simpson("Homer Simpson", 45, "Nuclear safety inspector")
        //val homer = Simpson(name, age, job)
        var homerTompson : Simpson

        homerTompson = Tompson(name, "Homer Thomson")

        homerTompson.age = age
        homerTompson.job  =job
        lblDemo.text = homerTompson.toString()
        //homer.age = 46
        //lblDemo.text = homer.toString()
    }

    fun changeActivity(view: View){


        //val intent = Intent(applicationContext, Main2Activity::class.java)
        //val intent = Intent(applicationContext, MainTimerActivity::class.java)
        val intent = Intent(applicationContext, MainRunanablesActivity::class.java)

        //Send Values througt intent
        intent.putExtra("Name", txtName.text.toString())
        startActivity(intent)
    }

    fun showAlert(view: View){

        var alert = AlertDialog.Builder(this)

        alert.setTitle("First Alert")
        alert.setMessage("We need a confirmation")
        alert.setPositiveButton("Ok"){dialogInterface : DialogInterface, i:Int -> Toast.makeText(applicationContext, "You confirmed the dialog", Toast.LENGTH_LONG).show() }
        alert.setNegativeButton("Cancel"){dialogInterface : DialogInterface, i:Int -> Toast.makeText(applicationContext, "You cancel the dialog", Toast.LENGTH_LONG).show() }
        alert.show()

    }

    fun showMaps(view: View) {
        val intent = Intent(applicationContext, MapsActivity::class.java)
        startActivity(intent)
    }

    fun showCurrency(view: View) {
        val intent = Intent(applicationContext, CurrencyActivity::class.java)
        startActivity(intent)
    }
}

