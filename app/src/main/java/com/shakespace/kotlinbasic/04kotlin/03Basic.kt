package com.shakespace.kotlinbasic.`04kotlin`

/**
与 Java 不同, Kotlin 中数组的类型是不可变的. 所以 Kotlin 不允许将一个 Array<String> 赋值给一个 Array<Any> , 否则可能会导
致运行时错误(但你可以使用 Array<out Any> , 参见 类型投射).

val price = """
${'$'}9.99
"""

凡是能够产生迭代器(iterator)的值, 都可以使用 for 进行遍历, 也就是说, 遍历对象需要满足以下条件:
—存在一个成员函数-或扩展函数 iterator(),它的返回类型应该:
—存在一个成员函数-或扩展函数 next(),并且
— 存在一个成员函数- 或扩展函数 hasNext() , 它的返回类型为 Boolean 类型.
上述三个函数都需要标记为 operator .


return@a 1
含义是 “返回到标签 @a 处, 返回值为 1 ”, 而不是 “返回一个带标签的表达式 (@a 1) ”.



 */

fun main() {

    get@get(1)


    listOf(1,2,3,4,5).forEach {
        if(it==3) return@forEach
        println(it)
    }
}

fun get(x: String) {
    when (val a = x) {
        "a" -> {
            a.length
            println("sss")
        }
        "b" -> println("111")
    }

}
