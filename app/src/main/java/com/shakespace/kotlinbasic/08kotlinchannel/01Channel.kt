package com.shakespace.kotlinbasic.`08kotlinchannel`

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 *
 * 延迟产生的数据提供了一种方便的方式可以在协程之间传递单个值.
 * 而通道则提供了另一种方式, 可以在协程之间传递数值的流.
 *
 *Channel 在概念上非常类似于 BlockingQueue . 关键的不同是,
 * 它没有阻塞的 put 操作, 而是提供挂起的 send 操作, 没有阻塞的 take
操作, 而是提供挂起的 receive 操作.
 *
 *
 */

fun main() = runBlocking {
    //sampleStart
    val channel = Channel<Int>()

    launch {
        // 这里可能是非常消耗 CPU 的计算工作, 或者是一段异步逻辑, 但在这个例子中我们只是简单地发送 5 个平方数
        for (x in 1..5) {
            delay(500)
            channel.send(x * x)
        }
        channel.close()
    }

    // 我们在这里打印收到的整数:
//    println(channel.receive())
    // 如果通道关闭后调用  会报错
//    repeat(5) { println(channel.receive()) }

    for (y in channel) println(y)
    println("Done!")


    println("Done!") //sampleEnd
}