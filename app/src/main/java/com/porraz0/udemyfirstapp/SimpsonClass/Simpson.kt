package com.porraz0.udemyfirstapp.SimpsonClass

open class Simpson(var name:String, var age:Int?, var job:String) {

    override fun toString(): String {
        return "$name $age $job"
    }
}

class Tompson(name: String, var alias: String) : Simpson(name, null, ""){

    override fun toString(): String {
        return "$alias $age $job"
    }

    fun TrueName(): String{
        return "$name $age $job"
    }
}