package com.shakespace.kotlinbasic.`07kotlincoroutines`

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 *
 * 本质上来说, 协程就是轻量级的线程. 在某个 CoroutineScope 的上下文环境内,
 * 协程通过 协程构建器 launch 来启动. 在上面的示例中,
 * 我们在 GlobalScope 内启动了新的协程, 也就是说,
 * 新协程的生命周期只受整个应用程序的生命周期限制.
 *
 *

 1. launch
 2. async
 3. withcontext

 4. GlobalScope :
    没有绑定任何Job ， 不会被Cancel
 5. Dispatcher 用来指定线程


 */
fun main() {

    GlobalScope.launch {
        delay(1000) // no block ,
        println("world")
    }

    println("hello")
    Thread.sleep(2000)

    runBlocking {
        delay(1000)  // will block thread
    }

}

