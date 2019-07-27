package com.shakespace.kotlinbasic.`12KotlinSelect`

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

/*
使用选择表达式, 我们可以同时等待多个挂起函数, 并且 选择 其中第一个执行完毕的结果.


 */

fun main() = runBlocking<Unit> {
    //sampleStart
    val fizz = fizz()
    val buzz = buzz()
    repeat(7) {
        selectFizzBuzz(fizz, buzz)
    }
    coroutineContext.cancelChildren() // 取消 fizz 和 buzz 协程 //sampleEnd
}


fun CoroutineScope.fizz() = produce<String> {
    while (true) { // 每 300ms 发送一个 "Fizz"
        delay(300)
        send("Fizz")
    }
}

fun CoroutineScope.buzz() = produce<String> {
    while (true) { // 每 500ms 发生一个 "Buzz!"
        delay(300)
        send("Buzz!")
    }
}

suspend fun selectFizzBuzz(fizz: ReceiveChannel<String>, buzz: ReceiveChannel<String>) {
    select<Unit> {
        // <Unit> 表示这个 select 表达式不产生任何结果值
        fizz.onReceive { value ->
            // 这是第 1 个 select 子句
            println("fizz -> '$value'")
        }
        buzz.onReceive { value ->
            // 这是第 2 个 select 子句
            println("buzz -> '$value'")
        }
    }
}