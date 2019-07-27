package com.shakespace.kotlinbasic.`01Kotlin1`.`1`

typealias Books = Map<String, String>

fun main() {
    var books: Books = mapOf("a" to "b")
//    books.get()
    val kFunction0 = ::asyncOverlay


    val kFunction01 = ::Object

    // 解构声明
    val map = mapOf(1 to "one", 2 to "two")
    // 以前的编码方式:
    println(map.mapValues { entry ->
        val (key, value) = entry
        "$key -> $value!"
    })
    // 现在的编码方式:
    println(map.mapValues { (key, value) -> "$key -> $value!" }) //sampleEnd

    // use _ to replace unused param
    println(map.mapValues { (_, value) -> "  -> $value!" }) //sampleEnd



}

//只能继承自 非数据类
sealed class Expr

data class Const(val number: Double) : Expr()

data class Sum(val num: Double) : Expr()



