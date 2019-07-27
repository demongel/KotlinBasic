package com.shakespace.kotlinbasic.`13SharedState`

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.runBlocking

// 供 counterActor 使用的消息类
sealed class CounterMsg

object IncCounter : CounterMsg() // 单向消息, 将计数器加 1
class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg() // 一个带应答的请求


// 这个函数启动一个新的计数器 actor
fun CoroutineScope.counterActor() = actor<CounterMsg> {
    var counter = 0 // actor 的状态值

    //  actorScope 自带一个channel
    for (msg in channel) { // 遍历所有收到的消息
        when (msg) {
            is IncCounter -> counter++
            is GetCounter -> msg.response.complete(counter)
        }
    }
}

/**
 * actor 是一个实体, 其中包含一个线程, 一个限定并封装在这个线程上的状态值,
以及一个用来与其他协程通信的通道. 一个简单的 actor 可 以写成一个函数,
但带有复杂状态的 actor 更适合写成一个类.

有一个 actor 协程构建器, 可以将 actor 的信箱通道(mailbox channel) 绑定在它的作用范围上,
用来接受消息, 并将它的送信通道绑定到线程构建器的结果任务对象上,
因此一个指向 actor 的引用就可以作为它的句柄来传递.

使用 actor 的第一步是定义 actor 将要处理的消息类.
Kotlin 的 封闭类(Sealed Class) 非常适合于这个目的.
我们定义一个 CounterMsg 封闭类, 其中 IncCounter 消息用来对计数器加 1,
GetCounter 消息用来获取计数器的值.
后一个消息还需要发送一个应答.
我们在这里 使用通信原语 CompletableDeferred 来实现这个目的, 它表示一个会在未来得到(传来)的单个的值,.
 */

fun main() = runBlocking<Unit> {
    //sampleStart
    val counter = counterActor() // 创建 actor
    GlobalScope.massiveRun {
        //  actor 发送数据
        // IncCounter 是个object 对象
        counter.send(IncCounter)
    }

// 发送一个消息, 从 actor 得到计数器值
    val response = CompletableDeferred<Int>()
    //  通过这个response 来取回数据
    counter.send(GetCounter(response))
    println("Counter = ${response.await()}")
    counter.close() // 关闭 actor

}

/*
对于结果的正确性来说) actor 本身在哪个上下文中运行是无关紧要的.
actor 是一个协程, 协程是顺序执行的, 因此将状态值限定在特定 的协程内,
可以解决共享的可变状态值问题. actor 确实可以修改它自己的私有状态值,
但它们之间只能通过消息来相互影响 (因此不必使 用锁).

在高负载的情况下, actor 比锁的效率更高, 因为这种情况下 actor 总是有工作可做(不必挂起),
 而且它完全不必在不同的上下文之间切换.
 */
/**
actor 协程构建器 与 produce 协程构建器刚好相反. actor 绑定到一个通道,
从通道读取消息, 而生产者则绑定到一个通道, 向 通道发送数据.

produce 用来读取
actor 用来发送
 */