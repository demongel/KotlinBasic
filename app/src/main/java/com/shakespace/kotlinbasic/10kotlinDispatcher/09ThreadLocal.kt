package com.shakespace.kotlinbasic.`10kotlinDispatcher`

import kotlinx.coroutines.*

val threadLocal = ThreadLocal<String?>() // 声明线程局部变量


/**
 * 对于 ThreadLocal, 有一个扩展函数 asContextElement 可以帮助我们.
 * 它会创建一个额外的上下文元素, 用来保持某个给定的 ThreadLocal 的值,
 * 并且每次当协程切换上下文时就恢复它的值.
 *
 */
fun main() = runBlocking<Unit> {
    //sampleStart
    threadLocal.set("main")
    println("Pre-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    val job = launch(
        Dispatchers.Default + threadLocal.asContextElement(value = "launch")
    ) {
        println("Launch start, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
        yield()
        println("After yield, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")
    }
    job.join()
    println("Post-main, current thread: ${Thread.currentThread()}, thread local value: '${threadLocal.get()}'")

}

/**
在这个示例程序中, 我们使用 Dispatchers.Default, 在后台线程池中启动了一个新的协程,
因此协程会在线程池的另一个线程中运行, 但它 还是会得到我们通过 threadLocal.asContextElement(value="launch")
指定的线程局部变量的值,无论协程运行在哪个线程内.
ThreadLocal 在协程中得到了一级支持, 可以在 kotlinx.coroutines 提供的所有基本操作一起使用.
它只有一个关键的限制: 当线程局部 变量的值发生变化时, 新值不会传递到调用协程的线程中去
(因为上下文元素不能追踪对 ThreadLocal 对象的所有访问) 而且更新后的值 会在下次挂起时丢失.
请在协程内使用 withContext 来更新线程局部变量的值, 详情请参见 asContextElement.
另一种方法是,值可以保存在可变的装箱类(mutablebox)中,比如 classCounter(vari:Int),
再把这个装箱类保存在线程局部变量中.然 而, 这种情况下, 对这个装箱类中的变量可能发生并发修改,
你必须完全负责对此进行同步控制.

对于高级的使用场景, 比如与日志 MDC(Mapped Diagnostic Context) 的集成, 与事务上下文(transactional context)的集成,
或者与其他内 部使用线程局部变量来传递数据的库的集成, 应该实现 ThreadContextElement 接口,

 */