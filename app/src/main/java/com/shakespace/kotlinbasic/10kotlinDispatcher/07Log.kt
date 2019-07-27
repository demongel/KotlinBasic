package com.shakespace.kotlinbasic.`10kotlinDispatcher`

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

/**
 * 上下文元素 CoroutineName 起到与线程名类似的作用.
 * 当 调试模式 开启时, 协程名称会出现在正在运行这个协程的线程的名称内
 */
fun main() = runBlocking(CoroutineName("main")) {
    //sampleStart
    log("Started main coroutine")
// 启动 2 个背景任务
    val v1 = async(CoroutineName("v1coroutine")) {
        delay(500)
        log("Computing v1")
        252
    }
    val v2 = async(CoroutineName("v2coroutine")) {
        delay(1000)
        log("Computing v2")
        6
    }
    log("The answer for v1 / v2 = ${v1.await() / v2.await()}") //sampleEnd
}