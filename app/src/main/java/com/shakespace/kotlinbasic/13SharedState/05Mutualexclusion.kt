package com.shakespace.kotlinbasic.`13SharedState`

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

// 互斥

/**

对于这个问题的另一个解决方案是互斥(Mutual exclusion),
它使用一个 临界区(critical section) 来保护所有针对共享状态值的修改动作,
临界区内的代码永远不会并发执行. 在阻塞式编程的世界,

你通常会使用 synchronized 或 ReentrantLock 来实现这个目的.

在协程中的 方案叫做 Mutex. 它的 lock 和 unlock 函数可以用来界定临界区.
主要的区别在于 Mutex.lock() 是一个挂起函数. 它不会阻塞线程.

还有一个扩展函数 withLock, 它代表 mutex.lock(); try { ... } finally { mutex.unlock() } :
 */

val mutex = Mutex()

fun main() = runBlocking<Unit> {
    //sampleStart
    GlobalScope.massiveRun {
        mutex.withLock {
            number++
        }
    }
    println("Counter = $number") //sampleEnd
}

// 193ms 时间略长
/*
示例程序中的锁是细粒度的, 因此会产生一些代价. 但是, 对于某些情况下,
你确实需要不时修改某些共享的状态值, 但是这个状态
值又没有限定在某个线程之内, 那么使用锁是一种好的选择.
 */