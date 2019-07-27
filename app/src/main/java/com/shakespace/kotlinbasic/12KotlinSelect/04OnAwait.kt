package com.shakespace.kotlinbasic.`12KotlinSelect`

import kotlinx.coroutines.*
import kotlinx.coroutines.selects.select
import kotlin.random.Random


//延迟一段随机长度的时间, 然后返回一个延迟 的字符串值:
fun CoroutineScope.asyncString(time: Int) = async {
    delay(time.toLong())
    "Waited for $time ms"
}

fun CoroutineScope.asyncStringsList(): List<Deferred<String>> {
    val random = Random(2)
    return List(12) {
        val int = random.nextInt(1000)
//        println(int)
        asyncString(int)
    }
}

//main 函数等待这些异步函数的第 1 个运行完毕, 然后统计仍处于激活状态的延迟值的数量.
fun main() = runBlocking<Unit> {

    //  12 个函数
    val list = asyncStringsList()

    val result = select<String> {
        list.withIndex().forEach { (index, deferred) ->
            //  通过  onwait 来选择延迟的值？？
            //  相当于 只拿到  一堆协程中 最先完成的部分
            //  这里都进行了运行，但是select 只要第一个
            println(deferred)
            deferred.onAwait { answer ->
                "Deferred $index produced answer '$answer'"
            }
        }
    }

    // 用await 可以都获得
//    list.forEach {
//        println(it.await())
//    }
    println("result= $result")
//    delay(1000)
    val countActive = list.count { it.isActive }
    println("$countActive coroutines are still active")
//sampleEnd
}


