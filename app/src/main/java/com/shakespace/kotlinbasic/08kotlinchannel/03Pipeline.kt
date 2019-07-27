package com.shakespace.kotlinbasic.`08kotlinchannel`

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking


@ExperimentalCoroutinesApi
fun main() = runBlocking {
    //sampleStart
    val numbers = produceNumbers() // 从 1 开始产生无限的整数
    val squares = square(numbers) // 对整数进行平方
    for (i in 1..5) println(squares.receive()) // 打印前 5 个数字
    println("Done!") // 运行结束
    coroutineContext.cancelChildren() // 取消所有的子协程
}

@ExperimentalCoroutinesApi
fun CoroutineScope.produceNumbers() = produce<Int> {
    var x = 1
    while (true) send(x++) // 从 1 开始递增的无限整数流
}

@ExperimentalCoroutinesApi
fun CoroutineScope.square(numbers: ReceiveChannel<Int>): ReceiveChannel<Int> = produce {
    for (x in numbers) send(x * x)
}