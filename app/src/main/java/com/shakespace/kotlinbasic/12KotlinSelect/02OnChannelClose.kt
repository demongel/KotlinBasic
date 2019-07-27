package com.shakespace.kotlinbasic.`12KotlinSelect`

import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.selects.select

fun main() = runBlocking<Unit> {
    //sampleStart
    val a = produce<String> {
        repeat(5) { send("Hello $it") }
    }
    val b = produce<String> {
        repeat(4) { send("World $it") }
    }
    repeat(9) {
        // 输出前 8 个结果
        println(selectAorB(a, b))
    }
    coroutineContext.cancelChildren()
}

suspend fun selectAorB(a: ReceiveChannel<String>, b: ReceiveChannel<String>): String =
    select<String> {
        a.onReceiveOrNull { value ->
            if (value == null)
                "Channel 'a' is closed"
            else
                "a -> '$value'"
        }
        b.onReceiveOrNull { value ->
            if (value == null)
                "Channel 'b' is closed"
            else
                "b -> '$value'"
        }
    }
/**
 *  当a 执行完毕之后 ，就会关闭，因而后面都取不到数据
 *
 *  首先, select 会 偏向 第 1 个子句. 如果同时存在多个通道可供选择,
 *  那么会优先选择其中的第 1 个.
 */

/*
a -> 'Hello 0'
a -> 'Hello 1'
b -> 'World 0'
a -> 'Hello 2'
a -> 'Hello 3'
b -> 'World 1'
a -> 'Hello 4'
Channel 'a' is closed
Channel 'a' is closed

 */