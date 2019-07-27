package com.shakespace.kotlinbasic.`07kotlincoroutines`

import kotlinx.coroutines.*


/**
 *
 * 在极少数情况下, 如果你需要在已被取消的协程中执行挂起操作,
 * 你可以使用 withContext 函数和 NonCancellable 上下文,
 * 把相应的代码包装在 withContext(NonCancellable) {...}
 */

fun main() = runBlocking {
    //sampleStart
    val job = launch {
        try {
            repeat(1000) { i ->
                println("I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            //  在finally中执行挂起操作
            withContext(NonCancellable) {
                println("I'm running finally")
                delay(1000L)
                println("And I've just delayed for 1 sec because I'm non-cancellable")
            }
        }
    }
    delay(1300L) // 等待一段时间
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // 取消 job, 并等待它结束
    println("main: Now I can quit.")
    //sampleEnd
}