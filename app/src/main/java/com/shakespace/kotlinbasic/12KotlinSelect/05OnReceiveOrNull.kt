package com.shakespace.kotlinbasic.`12KotlinSelect`

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.selects.select

fun main() = runBlocking<Unit> {

    val chan = Channel<Deferred<String>>() // 用来测试的通道

    launch {
        // 启动打印结果的协程
        //  传入channel  返回一个produce
        // 取出produce中的String
        for (s in switchMapDeferreds(chan))
            println(s) // 打印每个收到的字符串
    }

    //  100ms 后 发送 BEGIN 到produce
    chan.send(asyncString("BEGIN", 100))

    delay(200) // 延迟足够长的时间, 让 "BEGIN" 输出到通道
    chan.send(asyncString("Slow", 500))
    delay(100) // 延迟的时间不够长, "Slow" 没有输出到通道

    chan.send(asyncString("Replace", 100))
    delay(500) // 在发送最后一条测试数据之前等待一段时间

    chan.send(asyncString("END", 500))
    delay(1000) // 给它一点时间运行

    chan.close() // 关闭通道 ...
    delay(500) // 等待一段时间, 让它结束运行

}

fun CoroutineScope.switchMapDeferreds(input: ReceiveChannel<Deferred<String>>) = produce<String> {

    var current = input.receive() // 从第 1 个接收到的延迟值开始

//    println("----------"+current.await())

    while (isActive) { // 无限循环, 直到通道被取消/关闭

        //  select 一次只会选择一条来执行，如果有多个，执行第一个
        val next = select<Deferred<String>?> {
            // 这个 select 表达式返回下一个延迟值, 或者 null
            input.onReceiveOrNull
            input.onReceiveOrNull { update ->
                //                println("call send in input")
                println("input-----" + update) //DeferredCoroutine{Active}@782830e
                update // 改为等待下一个延迟值
            }

            //  似乎是有 await 已经有值的时候 先执行await？？
            //
            current.onAwait { value ->
                //
//                println("call send in onAwait")
                // 打印的值 从这里发送
                send(value) // 如果当前正在等待的延迟值已经产生, 将它发送出去
                // 然后从input 中取值 并返回给next
                //  如果不为null  赋值给current
                //  继续循环
                //  因为await 的时间没到，执行input？
                //
                val receiveOrNull = input.receiveOrNull()
                println("onAwait---------$receiveOrNull")
                receiveOrNull // 再继续使用从输入通道得到的下一个延迟值
            }
        }
//        println("---------$next")
        println("--------------${next?.await()}")
        if (next == null) {
            println("Channel was closed")
            break // 循环结束
        } else {
            current = next
        }
    }
}

//  wait for a time , and return string
fun CoroutineScope.asyncString(str: String, time: Long) = async {
    delay(time)
    str
}