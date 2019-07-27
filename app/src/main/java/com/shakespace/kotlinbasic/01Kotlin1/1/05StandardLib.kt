package com.shakespace.kotlinbasic.`01Kotlin1`.`1`

fun main() {

    val stringOne ="String one"

    val toIntOrNull = stringOne.toIntOrNull()

    val ten = "10"

    println(ten.toInt(2))
    println(ten.toInt(16))

    println(ten.toInt(6))
}