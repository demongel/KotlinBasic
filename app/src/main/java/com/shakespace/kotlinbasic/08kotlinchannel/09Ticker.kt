package com.shakespace.kotlinbasic.`08kotlinchannel`

import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull


/**
定时器(Ticker)通道是一种特别的会合通道(rendezvous channel),
每次通道中的数据耗尽之后, 它会延迟一个固定的时间, 并产生一个 Unit .
虽然它单独看起来好像毫无用处, 但它是一种很有用的零件, 可以创建复杂的基于时间的 produce 管道, 以及操作器,
执行窗口操作
和其他依赖于时间的处理. 定时器通道可以用在 select 中, 执行 “on tick” 动作.
可以使用 ticker 工厂函数来创建这种通道. 使用通道的 ReceiveChannel.cancel 方法来指出不再需要它继续产生数据了.

注意, ticker 会感知到消费端的暂停, 默认的, 如果消费端发生了暂停, 它会调整下一个元素产生的延迟时间, 尽量保证产生元素时维持一个 固定的间隔速度.
另外一种做法是, 将 mode 参数设置为 TickerMode.FIXED_DELAY, 可以指定产生元素时维持一个固定的间隔速度.

 */
@ObsoleteCoroutinesApi
fun main() = runBlocking<Unit> {
    //  这是一个通道
    val tickerChannel = ticker(delayMillis = 100, initialDelayMillis = 0) // 创建定时器通道

    //  取走第一个
    var nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
    println("Initial element is available immediately: $nextElement") // 初始指定的延迟时间还未过去

    // 此时新数据还未产生
    nextElement =
        withTimeoutOrNull(50) { tickerChannel.receive() } // 之后产生的所有数据的延迟时间都是 100ms
    println("Next element is not ready in 50 ms: $nextElement")

    //  新数据产生
    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
    println("Next element is ready in 100 ms: $nextElement")

// 模拟消费者端的长时间延迟
    println("Consumer pauses for 150ms")
    delay(150)

// 下一个元素已经产生了
    nextElement = withTimeoutOrNull(1) { tickerChannel.receive() }
    println("Next element is available immediately after large consumer delay: $nextElement") // 注意, `receive` 调用之间的暂停也会被计算在内,因此下一个元素产生得更快

    nextElement = withTimeoutOrNull(60) { tickerChannel.receive() }
    println("Next element is ready in 50ms after consumer pause in 150ms: $nextElement")

    tickerChannel.cancel() // 告诉通道, 不需要再产生更多元素了
}