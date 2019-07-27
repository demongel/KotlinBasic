package com.shakespace.kotlinbasic.`00KotlinDemo`

fun main() {

    val my= my("123",333)
}

open class Demo

class my(val name:String, private var age: Int):Demo(){

    //        set(value) {
//                println("age has be set$value")
//            }

    init {
        println("call init")
        this.age = age
        println("age set in init")
    }
}

fun shadow(num: Int): Unit {
    val num = 2
    if(num>0){
        val num = 3
    }

    println("num: = $num")
}