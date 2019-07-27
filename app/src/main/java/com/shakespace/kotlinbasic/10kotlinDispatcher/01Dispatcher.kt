package com.shakespace.kotlinbasic.`10kotlinDispatcher`

import kotlinx.coroutines.*

/**
协程的上下文是一组不同的元素.

协程上下文包含了一个 协程派发器 (参见 CoroutineDispatcher), 它负责确定对应的协程使用哪个或哪些线程来执行.
协程派发器可以将 协程的执行限定在某个特定的线程上, 也可以将协程的执行派发给一个线程池,
或者不加限定, 允许协程运行在任意的线程上.
所有的协程构建器, 比如 launch 和 async, 都接受一个可选的 CoroutineContext 参数,
这个参数可以用来为新创建的协程显式地指定派发 器, 以及其他上下文元素.


当 launch { ... } 没有参数时, 它将会从调用它的代码的
1. CoroutineScope 继承相同的上下文(因此也继承了相应的派发器).
在示例 程序中, 它继承了运行在 main 线程中主 runBlocking 协程的上下文.
2. Dispatchers.Unconfined 是一个特殊的派发器, 在示例程序中,
它似乎也是在 main 线程中执行协程, 但实际上, 它是一种不同的 机制, 我们在后文中详细解释.
3. 当协程在 GlobalScope 内启动时, 会使用默认派发器,
用 Dispatchers.Default 表示, 它会使用后台共享的线程池,
因此 launch(Dispatchers.Default){...} 会使用与 GlobalScope.launch{...} 相同的派发器.
4. newSingleThreadContext 会创建一个新的线程来运行协程.
一个专用的线程是一种非常昂贵的资源. 在真实的应用程序中,
当这样的线程, 必须在不再需要的时候使用 close 函数释放它, 或者保存在一个顶层变量中, 并在应用程序内继续重用.

 */

@ObsoleteCoroutinesApi
@ExperimentalCoroutinesApi
fun main() = runBlocking<Unit> {
    //sampleStart
    launch {
        // 使用父协程的上下文, 也就是 main 函数中的 runBlocking 协程
        println("main runBlocking : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Unconfined) {
        // 非受限 -- 将会在主线程中执行
        println("Unconfined : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(Dispatchers.Default) {
        // 会被派发到 DefaultDispatcher
        println("Default : I'm working in thread ${Thread.currentThread().name}")
    }
    launch(newSingleThreadContext("MyOwnThread")) {
        // 将会在独自的新线程内执行
        println("newSingleThreadContext: I'm working in thread ${Thread.currentThread().name}")
    }
//sampleEnd
}

/**
Unconfined : I'm working in thread main
Default : I'm working in thread DefaultDispatcher-worker-1
newSingleThreadContext: I'm working in thread MyOwnThread
main runBlocking : I'm working in thread main
 */