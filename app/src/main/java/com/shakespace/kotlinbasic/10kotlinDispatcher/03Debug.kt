package com.shakespace.kotlinbasic.`10kotlinDispatcher`

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking


fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

// 请使用 JVM 选项 -Dkotlinx.coroutines.debug 来运行下面的示例程序:
//  点击 运行箭头左边  下拉  edit configuration 设置vm 选项

fun main() = runBlocking<Unit> {
    //sampleStart
    val a = async {
        log("I'm computing a piece of the answer")
        6
    }
    val b = async {
        log("I'm computing another piece of the answer")
        7
    }
    log("The answer is ${a.await() * b.await()}") //sampleEnd
}