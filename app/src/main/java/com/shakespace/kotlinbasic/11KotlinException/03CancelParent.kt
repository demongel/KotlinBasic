package com.shakespace.kotlinbasic.`11KotlinException`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

/**
如果使用 Job.cancel 来取消一个协程, 而且不指明任何原因,
那么协程会终止运行, 但 不会取消它的父协程. 父协程可以使用不指明原因的取消机制,
来取消自己的子协程, 而不取消自己.
 *
 *
 * 如果一个协程遇到了 CancellationException 以外的异常,那么它会使用这个异常来取消自己的父协程.
 * 这种行为不能覆盖,而且Kotlin 使用这个机制来实现 结构化并发 中的稳定的协程层级关系,
 * 而不是依赖于 CoroutineExceptionHandler 的实现.
 * 当所有的子协程全部结 束后, 原始的异常会被父协程处理.


这也是为什么, 在这些示例程序中, 我们总是在 GlobalScope 内创建的协程上安装 CoroutineExceptionHandler.
如果在 main runBlocking 的作用范围内启动的协程上安装异常处理器, 是毫无意义的,
因为子协程由于异常而终止后之后, 主协程一定会被取消, 而忽略它上面安装的异常处理器.
 */
fun main() = runBlocking {
    //sampleStart
    val job = launch {
        val child = launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                println("Child is cancelled")
            }
        }
        yield()//  让出
        println("Cancelling child")
        child.cancel()
        child.join()
        yield()
        println("Parent is not cancelled")
    }
    job.join()
}

/**
 * result:
Cancelling child
Child is cancelled
Parent is not cancelled
 */