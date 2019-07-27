package com.shakespace.kotlinbasic.`01Kotlin1`.`1`

import java.io.File

/**
 *  sequence  惰性集合操作
 *  可以对集合元素进行链式操作，不像普通集合那样 需要重新开辟内存存储中间结果。
 *
 *  sequence 返回sequence的是中间操作
 *  返回其他值 则属于末端操作
 *
 *  创建：
 *      使用asSequence
 *      使用generateSequence
 *      Sequence<Int>.constrainOnce()   can be used only once
 *
 *  handle large amount , use sequence is more effective
 *
 */
fun main() {

//    generateSequence {  }
//    generateSequence(10,nextFunction = )



     calcTime {
         (0..10000000)
             .map { it+1 }
             .filter { it%2==0 }
             .count{it<10}
             .run {
                 println("result is $this")
             }
     }

    calcTime {
        (0..10000000)
            .onEach { it+1 }
            .filter { it%2==0 }
            .count{it<10}
            .run {
                println("result is $this")
            }
    }


     calcTime {
         (0..10000000)
             .asSequence()
             .map { it+1 }
             .filter { it%2==0 }
             .count{it<10}
             .run {
                 println("result is $this")
             }
     }

    "1".also {

    }

    "1".apply {

    }

    val outDirFile = File("xxx/sxx/aa.html").takeIf { it.exists() } ?: ""

    //  if true  return null
    println("22".takeUnless {
        it.toInt() > 2
    })

    val mutableMap = hashMapOf<String, String>("1" to "2")
        .toMap()
        .toMutableMap()

    val map = mutableMap - "1"

    minOf(11,22,33)
    maxOf(22,33,44)

    MutableList(10){0}


    //  输出格式化良好的 数组内容
    println(arrayOf(1, 2, 23, 4).contentToString())

}


fun calcTime(action:(()->Unit)?){
    val start =System.currentTimeMillis()

    action?.invoke()

    println("total time is ${System.currentTimeMillis() - start}")
}



