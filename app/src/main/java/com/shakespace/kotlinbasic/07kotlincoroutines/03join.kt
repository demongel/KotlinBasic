package com.shakespace.kotlinbasic.`07kotlincoroutines`

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    //sampleStart
    val job = GlobalScope.launch {
        // 启动新的协程, 并保存它的执行任务的引用
        delay(1000L)
        println("World!")
    }
    println("Hello,")
    job.join() // 等待, 直到子协程执行完毕
//sampleEnd
}