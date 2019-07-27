package com.shakespace.kotlinbasic.`11KotlinException`

import kotlinx.coroutines.*


fun main() = runBlocking {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }
    val job = GlobalScope.launch(handler) {
        launch {
            // 第 1 个子协程
            try {
                println("run first coroutine")
                delay(Long.MAX_VALUE)
            } finally {
                withContext(NonCancellable) {
                    println("Children are cancelled, but exception is not handled until all children terminate")
                    delay(100)
                    println("The first child finished its non cancellable block")
                }
            }
        }

        //  发生异常时，父协程取消，调用finally 中的方法
        //  所有的子协程全部结 束后, 原始的异常会被父协程处理.
        launch {
            // 第 2 个子协程
            delay(10)
            println("Second child throws an exception")
            throw ArithmeticException()
        }
    }
    job.join()
}

/**
Second child throws an exception
Children are cancelled, but exception is not handled until all children terminate
The first child finished its non cancellable block
Caught java.lang.ArithmeticException

 */