package com.shakespace.kotlinbasic.`01Kotlin1`.`1`

import java.util.*


fun needAnswer() = Random().nextBoolean()

fun main() {

    // 局部变量  委托属性
    val answer by lazy {
        println("calc here")
        100
    }

    if(needAnswer()){
        print("the answer is $answer")
    }else{
        println("sometimes no answer is the answer")
    }


}

// if  property do not have backing field , can use inline to modify get() method

public val<T> List<T>.lastIndex:Int
    inline get()=this.size-1



