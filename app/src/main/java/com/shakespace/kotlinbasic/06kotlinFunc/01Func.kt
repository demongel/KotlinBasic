package com.shakespace.kotlinbasic.`06kotlinFunc`

import java.util.Arrays.asList

/**
 * 只有一个参数可以标记为 vararg . 如果 vararg 参数不是函数的最后一个参数, 那么对于 vararg 参数之后的其他参数, 可以使用命名参 数语法来传递参数值,
 * 或者, 如果参数类型是函数, 可以在括号之外传递一个 Lambda 表达式.
 *
 *
中缀标记法(Infix notation)
使用 infix 关键字标记的函数, 也可以使用中缀标记法(infix notation)来调用(调用时省略点号和括号). 中缀函数需要满足以下条件:
— 必须是成员函数, 或者是扩展函数;
— 必须只有单个参数;
— 参数不能是 不定数量参数, 而且不能有 默认值.

kotlin 支持函数嵌套
即局部函数

 */
fun main() {

    //  * 展开操作符
    val a = arrayOf(1, 2, 3)
    val list = asList(-1, 0, *a, 4)


    fun sayHello() {
        println("hello")
    }

}

