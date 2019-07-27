package com.shakespace.kotlinbasic.`04kotlin`


/**
 * 扩展函数的调用派发过程是静态的, 也就是说, 它并不是接收者类型的虚拟成员.
 * 这就意味着, 调用扩展函数时, 具体被 调用的函数是哪一个,
 * 是通过调用函数的对象表达式的类型来决定的,
 * 而不是在运行时刻表达式动态计算的最终结果类型决定的.
 *
 *
 *
 */
fun main() {
    printFoo(D())


    CC().caller(DD()) // 打印结果为 "D.foo in C"
    C1().caller(DD()) // 打印结果为 "D.foo in C1" - 派发接受者的解析过程是虚拟的
    CC().caller(D1()) // 打印结果为 "D.foo in C" - 扩展接受者的解析过程是静态的

}

/**
 * 由于扩展属性实际上不会向类添加新的成员,
 * 因此无法让一个扩展属性拥有一个 后端域变量.
 * 所以, 对于扩展属性不允许存在初始化器.
 * 扩展属性的行为只能通过明确给定的取值方法与设值方法来定义.
 *
 */
val <T> List<T>.lastIndex: Int
    get() = size - 1


fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' 指代 list 实例
    this[index1] = this[index2]
    this[index2] = tmp
}

open class F
class D : F()

fun F.foo() = "c"
fun D.foo() = "d"

fun printFoo(c: F) {
    println(c.foo())
}


open class DD {}
class D1 : DD() {}
open class CC {
    open fun DD.foo() {
        println("D.foo in C")
    }

    open fun D1.foo() {
        println("D1.foo in C")
    }

    fun caller(d: DD) {
        d.foo() // 调用扩展函数
    }
}

class C1 : CC() {
    override fun DD.foo() {
        println("D.foo in C1")
    }

    override fun D1.foo() {
        println("D1.foo in C1")
    }
}

