package com.shakespace.kotlinbasic.`07kotlincoroutines`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * 我们可以在协程需要工作的那个特定的作用范围内启动协程, 而不是象我 们通常操作线程那样(线程总是全局的), 在 GlobalScope 内启动协程.
在我们的示例程序中, 有一个 main 函数, 它使用 runBlocking 协程构建器变换成了一个协程.
所有的协程构建器, 包括 runBlocking , 都 会向它的代码段的作用范围添加一个 CoroutineScope 的实例.
我们在这个作用范围内启动协程, 而不需要明确地 join 它们,
因为外层协 程 (在我们的示例程序中就是 runBlocking )
会等待它的作用范围内启动的所有协程全部完成. 因此, 我们可以把示例程序写得更简单一 些:

 */
fun main() = runBlocking {
    // this: CoroutineScope
    launch {
        // 在 runBlocking 的作用范围内启动新的协程
        delay(1000L)
        println("World!")
    }
    println("Hello,")
}