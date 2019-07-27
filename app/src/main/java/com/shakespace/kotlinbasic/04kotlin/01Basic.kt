package com.shakespace.kotlinbasic.`04kotlin`

fun main() {

    println("Hello World")

    var nums = 1..200
    println(nums
        .filter { it % 2 == 0 && it % 3 == 0 }
        .map {
            println(it)
            it + 0
        }
        .sum())

    listOf(1, 12, 3, 4)
    mapOf(1 to 2, 3 to 4)

    //swap
    var a = 1
    var b = 2
    a = b.also { b = a }

}


data class Customer(val name: String, val age: Int)

// Singleton
object Resource {
    val name = "single"
}