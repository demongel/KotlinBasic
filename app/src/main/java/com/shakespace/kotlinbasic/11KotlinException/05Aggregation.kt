package com.shakespace.kotlinbasic.`11KotlinException`

import android.os.Build
import android.support.annotation.RequiresApi
import kotlinx.coroutines.*
import java.io.IOException

/**
如果一个协程的多个子协程都抛出了异常, 那么会怎么样?
通常的规则是 “最先发生的异常优先”, 因此第 1 个发生的异常会被传递给异 常处理器.
但是这种处理发生可能会导致丢失其他异常,
比如, 如果另一个协程在它的 finally 块中抛出了另一个异常.
为了解决这个问题, 我们将其他异常压制(suppress)到最先发生的异常内.



有一种解决办法是, 我们可以将各个异常分别向外抛出,
但是这样一来 Deferred.await 部分就必须实现相同的机制,
捕获多个异常, 以保持异常抛出与捕获的一致性.
这就会导致协程内部的具体实现细节(比如, 它是否将部分工作代理给了自己的子协程)
暴露给了它 的异常处理器.

异常聚合机制目前只能在 Java version 1.7+ 以上版本才能正常工作
协程取消异常是透明的, 默认不会被聚合到其他异常中:

 */
@RequiresApi(Build.VERSION_CODES.KITKAT)
fun main() = runBlocking {

    val handler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception with suppressed ${exception.suppressed!!.contentToString()}")
    }
    val job = GlobalScope.launch(handler) {

        launch {
            try {
                delay(Long.MAX_VALUE)
            } finally {
                throw ArithmeticException()
            }
        }

        launch {
            delay(100)
            throw IOException()
        }
        delay(Long.MAX_VALUE)
    }
    job.join()
}
/**
 * io 异常先发生
 * 将arithmetic 异常压在io异常内
 */
//result
//Caught java.io.IOException with suppressed [java.lang.ArithmeticException]