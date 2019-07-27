package com.shakespace.kotlinbasic.`04kotlin`

import java.text.DecimalFormat


fun main() {
    println(formatPersentage(0.0001))
}

fun formatPersentage(num: Double): String {
    val df = DecimalFormat("0.00%")
    return df.format(num)
}