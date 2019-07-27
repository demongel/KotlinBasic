package com.shakespace.kotlinbasic.`13SharedState`

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
线程限定(Thread confinement) 是共享的可变状态值问题的一种解决方案,

**它把所有对某个共享值的访问操作都限定在唯一的一个线程内**

最典型的应用场景是 UI 应用程序,
所有的 UI 状态都被限定在唯一一个 事件派发(event-dispatch) 线程
或者叫 application 线程内. 通 过使用单线程的上下文, 可以很容易地对协程使用这种方案.
 */

//  启用了一个新线程
val counterContext = newSingleThreadContext("CounterContext")

var number = 0

fun main() = runBlocking<Unit> {
    //sampleStart
    GlobalScope.massiveRun {
        // 使用 DefaultDispathcer 运行每个协程
        // 每次都要进行切换
        withContext(counterContext) {
            // 但把所有的加 1 操作都限定在单一线程的上下文中
            number++
        }
    }
    println("Counter = $number") //sampleEnd
}
/*
(之前大概26ms， 现在811ms)
这段代码的运行速度会非常地慢, 因为它进行了 细粒度(fine-grained) 的线程限定.
每一次加 1 操作都必须使用 withContext, 从多线程的
Dispatchers.Default 上下文切换到单一线程上下文.
 */