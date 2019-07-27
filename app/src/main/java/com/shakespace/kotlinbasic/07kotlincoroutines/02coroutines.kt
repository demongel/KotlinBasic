package com.shakespace.kotlinbasic.`07kotlincoroutines`

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * runBlocking<Unit> { ... } 起一种适配器的作用, 用来启动最上层的主协程.
 * 我们明确指定了返回值类型为 Unit , 因为 Kotlin 语言中,
语法正确的 main 函数必须返回 Unit .
 */
fun main() = runBlocking<Unit> {
    // 启动主协程
    GlobalScope.launch {
        // 在后台启动新的协程, 然后继续执行当前程序
        delay(1000L)
        println("World!")
    }
    println("Hello,") // 主协程在这里立即继续执行
    delay(2000L) // 等待 2 秒, 保证 JVM 继续存在
}