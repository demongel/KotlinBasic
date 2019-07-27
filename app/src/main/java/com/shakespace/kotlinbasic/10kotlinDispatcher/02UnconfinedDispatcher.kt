package com.shakespace.kotlinbasic.`10kotlinDispatcher`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
Dispatchers.Unconfined 协程派发器会在调用者线程内启动协程, 但只会持续运行到第一次挂起点为止.
在挂起之后, 它会在哪个线程内 恢复执行, 这完全由被调用的挂起函数来决定.
非受限派发器(Unconfined dispatcher) 适用的场景是, 协程不占用 CPU 时间,
也不更新那 些限定于某个特定线程的共享数据(比如 UI).
 *
 */

fun main() = runBlocking<Unit> {
    //sampleStart
    launch(Dispatchers.Unconfined) {
        // 非受限 -- 将会在主线程中执行
        println("Unconfined : I'm working in thread ${Thread.currentThread().name}")
        delay(500)
        println("Unconfined : After delay in thread ${Thread.currentThread().name}")
    }
    launch {
        // 使用父协程的上下文, 也就是 main 函数中的 runBlocking 协程
        println("main runBlocking: I'm working in thread ${Thread.currentThread().name}")
        delay(1000)
        println("main runBlocking: After delay in thread ${Thread.currentThread().name}")
    }
}

/**
Unconfined : I'm working in thread main
main runBlocking: I'm working in thread main
Unconfined : After delay in thread kotlinx.coroutines.DefaultExecutor
main runBlocking: After delay in thread main

继承了 runBlocking {...} 协程的上下文的协程会在 main 线程内恢复运行,
而非受限的协程会在默认的执行器线程内恢复运行, 因为它是挂起函数 delay 所使用的线程.

非受限派发器是一种高级机制, 对于某些极端情况, 如果我们不需要控制协程在哪个线程上执行,
或者由于协程中的某些操作必须立 即执行, 因此对其进行控制会导致一些不希望的副作用,
这时使用非受限派发器就非常有用. 在通常的代码中不应该使用非受限派发器.

 */