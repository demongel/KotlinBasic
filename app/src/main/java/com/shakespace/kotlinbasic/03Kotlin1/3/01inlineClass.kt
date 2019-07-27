package com.shakespace.kotlinbasic.`03Kotlin1`.`3`

import kotlin.random.Random


fun main() {
// 下一行代码不会发生构造器调用, 而且在运行期 'name' 中只会包含字符串 "Kotlin"
    val name = Name("Kotlin")
    println(name.s)


    val nextInt = Random.nextInt()

    val keys = 'a'..'f'
    val map = keys.associateWith { it.toString().repeat(5).capitalize() }
    map.forEach { println(it) }

    println(Char.MAX_VALUE)
    println(Char.MIN_VALUE)

}

inline class Name(val s: String)