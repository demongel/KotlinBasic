package com.shakespace.kotlinbasic.`08kotlinchannel`

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 多个协程也可以向同一个通道发送数据
 *
 * 到目前为止我们演示的通道都没有缓冲区. 无缓冲区的通道只会在发送者与接收者相遇时(也叫做会合(rendezvous))传输数据.
 * 如果先调用 了发送操作, 那么它会挂起, 直到调用接收操作, 如果先调用接收操作, 那么它会被挂起, 直到调用发送操作.
 */
fun main() = runBlocking {
    // 创建 唯一的接收通道
    val channel = Channel<String>()

    launch { sendString(channel, "foo", 200L) }
    launch { sendString(channel, "BAR!", 500L) }

    repeat(6) {
        // 接收前 6 个字符串
        println(channel.receive())
    }

    coroutineContext.cancelChildren() // 取消所有的子协程, 让 main 函数结束 //sampleEnd
}

suspend fun sendString(channel: SendChannel<String>, s: String, time: Long) {
    while (true) {
        delay(time)
        channel.send(s)
    }
}