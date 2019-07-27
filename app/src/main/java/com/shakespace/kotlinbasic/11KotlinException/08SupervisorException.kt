package com.shakespace.kotlinbasic.`11KotlinException`

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope

/**
 * 常规任务与监控任务的另一个重要区别就是对异常的处理方式.
 * 每个子协程都应该通过异常处理机制自行处理它的异常.
 * 区别在于, 子协 程的失败不会传播到父协程中.
 */
fun main() = runBlocking {
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    supervisorScope {
        val child = launch(handler) {
            println("Child throws an exception")
            throw AssertionError()
        }

        // 先执行
        println("Scope is completing")
    }

    //  捕获了异常  但主协程还在
    println("Scope is completed")
}

/*
Scope is completing
Child throws an exception
Caught java.lang.AssertionError
Scope is completed
 */