package com.shakespace.kotlinbasic.`10kotlinDispatcher`

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 当一个协程在另一个协程的 CoroutineScope 内启动时, 它会通过 CoroutineScope.coroutineContext
 * 继承这个协程的上下文, 并且新协
程的 Job 会成为父协程的任务的一个 子任务. 当父协程被取消时,
它所有的子协程也会被取消, 并且会逐级递归, 取消子协程的子协程. 但是,
如果使用 GlobalScope 来启动一个协程, 那么这个协程不会被绑定到启动它的那段代码的作用范围,
并会独自运行

 in a word , run in GlobalScope will not be canceled by the other scope

 */
fun main() = runBlocking<Unit> {
// 启动一个协程, 处理某种请求
    val request = launch {
        // 它启动 2 个其他的任务, 其中一个使用 GlobalScope
        GlobalScope.launch {
            println("job1: I run in GlobalScope and execute independently!")
            delay(1000)
            println("job1: I am not affected by cancellation of the request")
        }
// 另一个继承父协程的上下文
        launch {
            delay(100)
            println("job2: I am a child of the request coroutine")
            delay(1000)
            println("job2: I will not execute this line if my parent request is cancelled")
        }
    }
    delay(500)
    request.cancel() // 取消对请求的处理
    delay(1000) // 延迟 1 秒, 看看结果如何
    println("main: Who has survived request cancellation?")
//sampleEnd
}