package com.shakespace.kotlinbasic.`07kotlincoroutines`

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull

/**
 * withTimeout 函数抛出的 TimeoutCancellationException 异常是 CancellationException 的子类.
 * 我们在前面的例子中, 都没有看到过 CancellationException 异常的调用栈被输出到控制台.
 * 这是因为, 在被取消的协程中 CancellationException 被认为是协程结束的一个正 常原因.
 * 但是, 在这个例子中我们直接在 main 函数内使用了 withTimeout .
 *
 *
 * if use withTimeoutOrNull  will return null when exception happen
 *
 */
fun main() = runBlocking {
    //sampleStart

    val result = withTimeoutOrNull(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }

    println(result)

    withTimeout(1300L) {
        repeat(1000) { i ->
            println("I'm sleeping $i ...")
            delay(500L)
        }
    }
//sampleEnd
}