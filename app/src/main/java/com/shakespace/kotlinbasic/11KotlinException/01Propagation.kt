package com.shakespace.kotlinbasic.`11KotlinException`

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 协程构建器对于异常的处理有两种风格: 自动传播异常(launch 和 actor 构建器),
 * 或者将异常交给使用者处理(async 和 produce 构建器).
 * 前一种方式部队异常进行处理, 类似于 Java 的 Thread.uncaughtExceptionHandler ,
 * 后一种则要求使用者处理最终的异常, 比如使用 await 或 receive 来处理异常.
 *
 */
fun main() = runBlocking {
    val job = GlobalScope.launch {
        println("Throwing exception from launch")
        throw IndexOutOfBoundsException() // 这个异常会被 Thread.defaultUncaughtExceptionHandler 打印到控制台
    }
    job.join()
    println("Joined failed job")
    val deferred = GlobalScope.async {
        println("Throwing exception from async")
        throw ArithmeticException() // 这个异常不会被打印, 由使用者调用 await 来得到并处理这个异常
    }
    try {
        deferred.await()
        println("Unreached")
    } catch (e: ArithmeticException) {
        println("Caught ArithmeticException")
    }

}