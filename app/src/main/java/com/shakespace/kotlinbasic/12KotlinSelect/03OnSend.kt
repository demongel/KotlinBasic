package com.shakespace.kotlinbasic.`12KotlinSelect`

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select


fun main() = runBlocking<Unit> {
    //sampleStart
    val side = Channel<Int>() // 创建 side 通道

    launch {
        // 这是 side 通道上的一个非常快速的消费者
        side.consumeEach { println("Side channel has $it") }
    }

    produceNumbers(side).consumeEach {
        println("Consuming $it")
        delay(250) // 我们多花点时间慢慢分析这个数值, 不要着急
    }

    println("Done consuming")

    coroutineContext.cancelChildren()
}

fun CoroutineScope.produceNumbers(side: SendChannel<Int>) = produce<Int> {
    for (num in 1..10) { // 产生 10 个数值, 从 1 到 10
        delay(100) // 每隔 100 ms
        select<Unit> {
            //  事实上 是向两个都发送了？
            //  第一次过后 main  挂起   于是被side 消费
            //   250ms后  main 继续
            onSend(num) {} // 发送到主通道
            side.onSend(num) {} // 或者发送到 side 通道
        }
    }
}

/*
Consuming 1
Side channel has 2
Side channel has 3
Consuming 4
Side channel has 5
Side channel has 6
Consuming 7
Side channel has 8
Side channel has 9
Consuming 10
Done consuming
 */