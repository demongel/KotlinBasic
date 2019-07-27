package com.shakespace.kotlinbasic.`07kotlincoroutines`

import kotlinx.coroutines.*

fun main() = runBlocking {
    //sampleStart
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {

        try {

            var nextPrintTime = startTime
            var i = 0
            while (isActive) { // 可被取消的计算循环
// 每秒打印信息 2 次
                if (System.currentTimeMillis() >= nextPrintTime) {
                    println("I'm sleeping ${i++} ...")
                    nextPrintTime += 500L
                }
            }

        } finally {
            // 在被取消时会抛出 CancellationException 异常
        }
    }
    delay(1300L) // 等待一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消 job, 并等待它结束
    println("main: Now I can quit.")
    //sampleEnd
}