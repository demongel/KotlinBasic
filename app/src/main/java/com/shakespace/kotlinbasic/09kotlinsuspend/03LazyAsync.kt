package com.shakespace.kotlinbasic.`09kotlinsuspend`

import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
将可选的 start 参数设置为 CoroutineStart.LAZY, 可以让 async 延迟启动.
只有在通过 await 访问协程的计算结果的时候,
或者调用 start 函数的时候, 才会真正启动协程.

程序员负责决定什么时候调用 start 函数来明确地启动协程的执行.
我们 先启动了 one , 然后启动了 two , 然后等待两个协程分别结束.

如果我们在 println 内调用 await, 而省略了对各个协程的 start 调用,
那么两个协程的执行结果将会是连续的, 而不是并行的, 因为 await 会启动协程并一直等待执行结束,
这并不是我们使用延迟加载功能时期望的效果. 如果计算中使用到的值来自挂起函数的话,
可以使 用 async(start = CoroutineStart.LAZY) 来代替标准的 lazy 函数.

 测试： 不使用start  时间大概是两倍
 */
fun main() = runBlocking<Unit> {
    //sampleStart
    val time = measureTimeMillis {
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() } // 执行某些计算
        one.start() // 启动第一个协程
        two.start() // 启动第二个协程
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms") //sampleEnd
}