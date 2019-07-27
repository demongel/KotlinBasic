package com.shakespace.kotlinbasic.`08kotlinchannel`

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Channel() 工厂函数和 produce 构建器都可以接受一个可选的 capacity 参数,
 * 用来指定 缓冲区大小. 缓冲区可以允许发送者在挂起之前 发送多个数据,
 * 类似于指定了容量的 BlockingQueue , 它会在缓冲区已满的时候发生阻塞.
 */
fun main() = runBlocking<Unit> {
    //sampleStart
    val channel = Channel<Int>(4) // 创建带缓冲区的通道

    //前 4 个数据会被添加到缓冲区中, 然后在试图发送第 5 个数据时, 发送者协程会挂起.
    val sender = launch {
        // 启动发送者协程
        repeat(10) {
            println("Sending $it") // 发送数据之前, 先打印它
            channel.send(it) // 当缓冲区满时, 会挂起
        }
    }
// 不接收任何数据, 只是等待
    delay(1000)
    sender.cancel() // 取消发送者协程 //sampleEnd
}

