package com.shakespace.kotlinbasic.`13SharedState`

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 使用多线程的派发器, 比如 Dispatchers.Default, 协程可以并发执行.
 * 因此协程也面对并发带来的所有问题. 主要问题是访问 共享的可变状 态值 时的同步问题.
 * 在协程的世界里, 这类问题的有些解决方案与在线程世界中很类似, 但另外一些方案就非常不同.
 */
suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100 // 启动的协程数量
    val k = 1000 // 每个协程执行操作的重复次数

    val time = measureTimeMillis {
        val jobs = List(n) {
            launch {
                repeat(k) { action() }
            }
        }
        jobs.forEach { it.join() }
    }
    println("Completed ${n * k} actions in $time ms")
}

@Volatile // 在 Kotlin 中, `volatile` 是注解
var counter = 0

fun main() = runBlocking<Unit> {
    //sampleStart
    GlobalScope.massiveRun {
        counter++
    }
    println("Counter = $counter") //sampleEnd
}
/*
1. 这段代码的最终输出结果是什么? 很大概率它不会输出 “Counter = 100000”,
 因为 100 个协程运行在多线程环境内, 它们同时给 counter 加 1, 但没有任何的同步机制.

 2. 加上Volatile 不能解决问题 ，时间会略微增加
 因为 volatile 变量保证线性的(linearizable) (意思就是 “原子性
(atomic)”) 读和写操作, 但不能保证更大的操作(在我们的例子中, 就是加 1 操作)的原子性.



 */