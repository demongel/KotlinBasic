package com.shakespace.kotlinbasic.`08kotlinchannel`

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce

/**
1。 多个协程可能会从同一个通道接收数据, 并将计算工作分配给这多个协程.
我们首先来创建一个生产者协程, 它定时产生整数(每秒 10 个整 数):
 *
 *
 */
fun main() = runBlocking<Unit> {
    //sampleStart
    val producer = produceNumbers2()
    repeat(5) {
        launchProcessor(it, producer)
    }
    delay(950)
    producer.cancel() // 取消生产者协程, 因此也杀死了所有其他数据处理协程
//sampleEnd
}

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNumbers2() = produce<Int> {
    var x = 1 // 从 1 开始
    while (true) {
        send(x++) // 产生下一个整数
        delay(100) // 等待 0.1 秒
    }
}

/**
 * 取消生产者协程会关闭它的通道, 因此最终会结束各个数据处理协程中对这个通道的迭代循环.
 * 而且请注意, 在 launchProcessor 中, 我们是如何使用 for 循环明确地在通道上进行迭代,
 * 来实现扇出(fan-out). 与 consumeEach 不同, 这个 for 循环模式完全可以安全 地用在多个协程中.
 * 如果某个数据处理协程失败, 其他数据处理协程还会继续处理通道中的数据,
 * 而使用 consumeEach 编写的数据处理 协程, 无论正常结束还是异常结束, 总是会消费(取消) 它的通道.
 *
 *
 */
fun CoroutineScope.launchProcessor(id: Int, channel: ReceiveChannel<Int>) = launch {
    for (msg in channel) {
        println("Processor #$id received $msg")
    }
}