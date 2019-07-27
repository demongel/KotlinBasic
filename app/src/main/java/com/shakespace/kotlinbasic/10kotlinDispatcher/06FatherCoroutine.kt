package com.shakespace.kotlinbasic.`10kotlinDispatcher`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 父协程总是会等待它的所有子协程运行完毕. 父协程不必明确地追踪它启动的子协程,
 * 也不必使用 Job.join 来等待子协程运行完毕:
 *
 */
fun main() = runBlocking<Unit> {
    //sampleStart
// 启动一个协程, 处理某种请求
    val request = launch {
        repeat(3) { i ->
            // 启动几个子协程
            launch {
                delay((i + 1) * 200L) // 各个子协程分别等待 200ms, 400ms, 600ms
                println("Coroutine $i is done")
            }
        }
        println("request: I'm done and I don't explicitly join my children that are still active")
    }
    request.join() // 等待 request 协程执行完毕, 包括它的所有子协程
    println("Now processing of the request is complete")
}