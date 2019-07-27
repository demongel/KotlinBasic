package com.shakespace.kotlinbasic.`08kotlinchannel`

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    //sampleStart
    var cur = numbersFrom(2) //  获取从2 开始到数据

    for (i in 1..10) {
        val prime = cur.receive()
        println(prime)
        cur = filter(cur, prime)
    }

    coroutineContext.cancelChildren() // 取消所有的子协程, 让 main 函数结束 //sampleEnd
}

@ExperimentalCoroutinesApi
fun CoroutineScope.numbersFrom(start: Int) = produce<Int> {
    var x = start
    while (true) send(x++) // 从 start 开始递增的无限整数流
}

@ExperimentalCoroutinesApi
fun CoroutineScope.filter(numbers: ReceiveChannel<Int>, prime: Int) = produce<Int> {
    for (x in numbers)
        if (x % prime != 0)
            send(x)
}