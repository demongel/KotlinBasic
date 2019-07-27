package com.shakespace.kotlinbasic.`10kotlinDispatcher`

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * 有些时候我们会需要对协程的上下文定义多个元素. 这时我们可以使用 + 操作符.
 * 比如, 我们可以同时使用明确指定的派发器, 以及明确 指定的名称, 来启动一个协程
 */

fun main() = runBlocking<Unit> {
    //sampleStart
    launch(Dispatchers.Default + CoroutineName("test")) {
        println("I'm working in thread ${Thread.currentThread().name}")
    }
//sampleEnd
}

/**
假设我们的应用程序中有一个对象, 它存在一定的生命周期, 但这个对象不是 一个协程. 比如, 我们在编写一个 Android 应用程序, 在一个 Android activity 的上下文内启动了一些协程, 执行一些异步操作, 来取得并更 新数据, 显示动画, 等等等等. 当 activity 销毁时, 所有这些协程都必须取消, 以防内存泄漏.
我们创建Job的实例,并将它与activity的生命周期相关联,以此来管理协程的生命周期.当activity创建时,使用工厂函数 Job()来创建任 务的实例, 当 activity 销毁时取消这个任务,

class Activity : CoroutineScope
{ lateinit var job: Job
fun create() { job = Job()
}
fun destroy() { job.cancel()
}

......
 */