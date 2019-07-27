package com.shakespace.kotlinbasic.`11KotlinException`

import kotlinx.coroutines.*

/**
协程的上下文元素 CoroutineExceptionHandler 会被作为协程的通
用的 catch 块, 我们可以在这里实现自定义的日志输出, 或其他异常处理逻辑.
它的使用方法与 Thread.uncaughtExceptionHandler 类似.


可以通过 ServiceLoader 注册一个 CoroutineExceptionHandler, 为所有的协程重定义全局的异常处理器.
全局的异常处理器 类似于 Thread.defaultUncaughtExceptionHandler,
如果没有注册更具体的异常处理器, 就会使用这个
Thread.defaultUncaughtExceptionHandler 异常处理器.

在 Android 平台, 默认安装的协程全局异常处理器是 uncaughtExceptionPreHandler .


有些异常是我们预计会被使用者处理的, 只有发生了这类异常以外的其他异常时, 才会调用 CoroutineExceptionHandler,
因此, 对 async 或其他类似的协程构建器注册异常处理器, 不会产生任何效果.
 *
 *
 *
 */

fun main() = runBlocking {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    val job = GlobalScope.launch(handler) {
        throw AssertionError()
    }

    //  异常发生时  主协程也就取消了 因而忽略了异常处理
    val deferred = GlobalScope.async(handler) {
        throw ArithmeticException() // 这个异常不会被打印, 由使用者调用 deferred.await() 来得到并处理这个异常
    }
    joinAll(job, deferred)
}