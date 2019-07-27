package com.shakespace.kotlinbasic.`09kotlinsuspend`

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 由于 async 协程构建器被定义为 CoroutineScope 上的扩展函数,
 * 因此我们使用这个函数时就需要在作用范围 内存在 CoroutineScope,
 * coroutineScope 函数可以为我们提供 CoroutineScope:
 *
 * 通过协程的父子层级关系, 取消总是会层层传递到所有的子协程, 以及子协程的子协程:
 */
fun main() = runBlocking<Unit> {
    //sampleStart
    val time = measureTimeMillis {
        println("The answer is ${concurrentSum()}")
    }
    println("Completed in $time ms") //sampleEnd
}

/**
 * 通过这种方式, 如果 concurrentSum 函数内的某个地方发生错误,
 * 抛出一个异常, 那么在这个函数的作用范围内启动的所有协程都会被 取消.
 */
suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}