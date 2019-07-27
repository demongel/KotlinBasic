package com.shakespace.kotlinbasic.`13SharedState`

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking


/**
在实际应用中, 通常在更大的尺度上进行线程限定,
比如, 将大块的状态更新业务逻辑限定在单个线程中. (不针对一个具体的操作)

下面的示例程序就是这样做的, 它在单一线程的上下文中运行每个协程.

这里我们使用 CoroutineScope() 函数来将协程的上下文转换为 CoroutineScope 类型

 */
fun main() = runBlocking<Unit> {

    CoroutineScope(counterContext).massiveRun {
        number++
    }
    println("Counter = $number") //sampleEnd
}