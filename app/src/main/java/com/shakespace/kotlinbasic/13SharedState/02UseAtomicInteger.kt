package com.shakespace.kotlinbasic.`13SharedState`

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

/**
 * 一种对于线程和协程都能够适用的解决方案是, 使用线程安全的 (也叫 同步的(synchronized),
 * 线性的(linearizable), 或者 原子化的 (atomic)) 数据结构,
 * 这些数据结构会对需要在共享的状态数据上进行的操作提供需要的同步保障.
 * 在我们的简单的计数器示例中, 可以使 用 AtomicInteger 类,
 * 它有一个原子化的 incrementAndGet 操作:
 */
var count = AtomicInteger()

fun main() = runBlocking<Unit> {
    //sampleStart
    GlobalScope.massiveRun {
        count.incrementAndGet()
    }
    println("Counter = ${count.get()}") //sampleEnd
}

/*

对于这个具体的问题, 这是最快的解决方案. 这种方案适用于计数器, 集合, 队列, 以及其他标准数据结构,
以及这些数据结构的基本操作.
但是, 这种方案并不能简单地应用于复杂的状态变量, 或者那些没有现成的线程安全实现的复杂操作.
 */