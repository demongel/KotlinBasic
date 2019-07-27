package com.shakespace.kotlinbasic.`09kotlinsuspend`

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


/**
 * 如果在 doSomethingUsefulOne 和 doSomethingUsefulTwo 的调用之间不存在依赖关系,
 * 我们想要 并发地 执行这两个函数, 以便更快
得到结果, 那么应该怎么做? 这时 async 可以帮助我们.
概念上来说, async 就好象 launch 一样. 它启动一个独立的协程, 也就是一个轻量的线程,
与其他所有协程一起并发执行. 区别在于, launch 返回一个 Job, 其中不带有结果值,
而 async 返回一个 Deferred – 一个轻量的, 非阻塞的 future, 代表一个未来某个时刻可以得
到的结果值. 你可以对一个延期值(deferred value)使用 .await() 来得到它最终的计算结果,
但 Deferred 同时也是一个 Job , 因此如果需 要的话, 你可以取消它.

简要：
launch 返回Job 不带结果
async  带结果

注意, 协程的并发总是需要明确指定的.
 */
fun main() = runBlocking<Unit> {
    //sampleStart
    val time = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $time ms") //sampleEnd
}