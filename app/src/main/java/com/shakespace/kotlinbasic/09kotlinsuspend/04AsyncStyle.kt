package com.shakespace.kotlinbasic.`09kotlinsuspend`

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis


/**
 * 这些 xxxAsync 函数 不是 挂起 函数. 这些函数可以在任何地方使用.
 * 但是, 使用这些函数总是会隐含着异步执行(这里的意思是 并 发)它内部的动作.
 *
 *
 *  在这个例子中展示的这种使用异步函数的编程风格只是为了演示目的, 但在其他编程语言中是一种很流行的风格.
 *  我们 强烈不鼓励 在 Kotlin 协程中使用这种编程风格.
 *
 *如果在 val one=somethingUsefulOneAsync() 和 one.await() 表达式之间,代码存在某种逻辑错误,程序抛出了一个异常,程 序的操作中止了,
 * 那么会怎么样. 通常来说, 一个全局的错误处理器可以捕获这个异常, 将这个错误输出到 log, 报告给开发者,
 * 但程序仍然 可以继续运行, 执行其他的操作. 但在这里,
 * 尽管负责启动 somethingUsefulOneAsync 的那部分程序其实已经中止了,
 * 但它仍然会在后台 继续运行. 如果使用结构化并发(structured concurrency)方式话, 就不会发生这种问题,
 *
 */
// somethingUsefulOneAsync 函数的返回值类型是 Deferred<Int>
fun somethingUsefulOneAsync() = GlobalScope.async {
    doSomethingUsefulOne()
}

// somethingUsefulTwoAsync 函数的返回值类型是 Deferred<Int>
fun somethingUsefulTwoAsync() = GlobalScope.async {
    doSomethingUsefulTwo()
}

// 注意, 这个示例中我们没有在 `main` 的右侧使用 `runBlocking`
fun main() {
    val time = measureTimeMillis {
        // 我们可以在协程之外初始化异步操作
        val one = somethingUsefulOneAsync()
        val two = somethingUsefulTwoAsync()
// 但是等待它的执行结果必然使用挂起或阻塞.
// 这里我们使用 `runBlocking { ... }`, 在等待结果时阻塞主线程
        runBlocking {
            println("The answer is ${one.await() + two.await()}")
        }
    }
    println("Completed in $time ms")
}