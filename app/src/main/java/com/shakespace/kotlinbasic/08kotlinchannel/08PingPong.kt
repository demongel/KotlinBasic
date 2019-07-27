package com.shakespace.kotlinbasic.`08kotlinchannel`

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
如果从多个协程中调用通道的发送和接收操作, 从调用发生的顺序来看,
这些操作是 平等的. 通道对这些方法以先进先出(first-in first-out) 的顺序进行服务,
也就是说, 第一个调用 receive 的协程会得到通道中的数据. 在下面的示例程序中, 有两个 “ping” 和 “pong” 协程,
从公用的一个 “table” 通道接收 “ball” 对象.
 */


//sampleStart
data class Ball(var hits: Int)

fun main() = runBlocking {
    val table = Channel<Ball>() // 一个公用的通道
    launch { player("ping", table) }
    launch { player("pong", table) }
    table.send(Ball(0)) // 把 ball 丢进通道
    delay(1000) // 延迟 1 秒
    coroutineContext.cancelChildren() // 游戏结束, 取消所有的协程
}

/**
 * “ping” 协程首先启动, 因此它会先接收到 ball. 虽然 “ping” 协程将 ball 送回到 table 之后,
 * 立即再次开始接收 ball, 但 ball 会被
“pong” 协程接收到, 因为它一直在等待:

由于使用的执行器(executor)的性质, 有时通道的运行结果可能看起来不是那么平等.
 */
suspend fun player(name: String, table: Channel<Ball>) {
    for (ball in table) { // 使用 for 循环不断地接收 ball
        ball.hits++
        println("$name $ball")
        delay(300) // 延迟一段时间
        table.send(ball) // 把 ball 送回通道内
    }
}