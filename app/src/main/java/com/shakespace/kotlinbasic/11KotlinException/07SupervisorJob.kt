package com.shakespace.kotlinbasic.`11KotlinException`

import kotlinx.coroutines.*


/**
--->  如何让子协程只取消自己
而同时 父协程可以监控 被取消的协程 ，以便进行重启

 */

fun main() = runBlocking {
    //  创建一个job
    val supervisor = SupervisorJob()

    //  通过coroutineContext 和 supervisor 构建Scope

    with(CoroutineScope(coroutineContext + supervisor)) {

        // 启动第 1 个子协程 -- 在这个示例程序中, 我们会忽略它的异常 (实际应用中不要这样做!)
        val firstChild = launch(CoroutineExceptionHandler { _, _ -> }) {
            println("First child is failing")
            throw AssertionError("First child is cancelled")
        }

        // 启动第 2 个子协程
        val secondChild = launch {
            firstChild.join()
            // 第 1 个子协程的取消不会传播到第 2 个子协程
            println("First child is cancelled: ${firstChild.isCancelled}, but second one is still active")
            try {
                delay(Long.MAX_VALUE)
            } finally {
                // 但监控任务的取消会传播到第 2 个子协程
                println("Second child is cancelled because supervisor is cancelled")
            }
        }
        // 等待第 1 个子协程失败, 并结束运行
        firstChild.join()

        println("Cancelling supervisor")
        supervisor.cancel()

        // 顺序？
        secondChild.join()

    }
}
/*
First child is failing
First child is cancelled: true, but second one is still active
Cancelling supervisor
Second child is cancelled because supervisor is cancelled

 */