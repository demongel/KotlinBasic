package com.shakespace.kotlinbasic.`01Kotlin1`.`1`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay


suspend fun main() {


//    val result = asyncOverlay().await()

//    yield 和 yieldAll 函数可以产生 延迟生成的序列(lazily generated sequences),
//    标准库使用协程来支持这种功能. 在这类序列中, 当每个 元素被取得之后，
//    产生序列元素的代码段会被暂停, 当请求下一个元素时, 代码的执行又会回复.
    val seq = sequence {
        for (i in 1..5) {
            yield(i * i)
        }

        yieldAll(26..30)
    }

    println(seq.toList())

}


fun asyncOverlay() = GlobalScope.async(Dispatchers.Default) {
    val one = asyncGetString1("one")
    val two = asyncGetString2("two")

    println(one + two)
}

suspend fun asyncGetString1(str: String): String {

    delay(1000)

    println("one")
    return str
}

suspend fun asyncGetString2(str: String): String {

    delay(1000)

    println("two")
    return str
}

