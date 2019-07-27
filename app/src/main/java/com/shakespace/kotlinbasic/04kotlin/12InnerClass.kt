package com.shakespace.kotlinbasic.`04kotlin`

/**
 * 内联类不会创建新的对象，只会以基本类型存在
 *
 * 相当于将类中的数据内联到其他地方。
 *
 * 初看起来, 内联类好像非常象 类型别名. 确实, 它们都声明了一个新的类型,
 * 并且在运行期都表达为各自的底层类型.
但是, 主要的差别在于, 类型别名与它的底层类型是 赋值兼容 的
(与同一个底层类型的另一个类型别名, 也是兼容的),
而内联类不是如此. 也就是说, 内联类会生成一个真正的 新 类型,
相反, 类型别名只是给既有的类型定义了一个新的名字(也就是别名):
 */
fun main() {


    val test= Test("kotlin")
    test.say()
    println(test.a)

}

inline class Test(val s: String) {
    fun say() {
        println(s)
    }

    val a: String
        get() = s

}
