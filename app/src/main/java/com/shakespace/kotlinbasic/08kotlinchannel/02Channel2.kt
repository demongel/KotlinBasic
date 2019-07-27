package com.shakespace.kotlinbasic.`08kotlinchannel`

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking

fun CoroutineScope.produceSquares(): ReceiveChannel<Int> = produce {
    for (x in 1..5) send(x * x)
}

/**
 *构建通道的生产者(Producer)
在协程中产生一个数值序列, 这是很常见的模式. 这是并发代码中经常出现的 生产者(producer)/消费者(consumer) 模式的一部分.
你可以 将生产者抽象为一个函数, 并将通道作为函数的参数, 然后向通道发送你生产出来的值,
但这就违反了通常的函数设计原则, 也就是函数的 结果应该以返回值的形式对外提供.
有一个便利的协程构建器, 名为 produce, 它可以很简单地编写出生产者端的正确代码,
还有一个扩展函数 consumeEach, 可以在消费者 端代码中替代 for 循环:

 */

fun main() = runBlocking {
    //sampleStart
    val squares = produceSquares()

    squares.consumeEach { println(it) }

    println("Done!")
//sampleEnd
}