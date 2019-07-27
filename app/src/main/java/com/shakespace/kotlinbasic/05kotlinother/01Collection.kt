package com.shakespace.kotlinbasic.`05kotlinother`

fun main() {

}
/**
 *
Kotlin 没有专门的语法用来创建 list 和 set. 你可以使用标准库中的方法,
比如 listOf() , mutableListOf() , setOf() , mutableSetOf() .
在 并不极端关注性能的情况下, 创建 map 可以使用一个简单的 惯用法: mapOf(a to b, c to d) .


针对只读集合进行某种操作并返回一个可修改的集合 (比如 + , filter , drop , 等等操作.),
这时结果集合的创建过程本身 不是一个原子操作(atomically),
因此, 在另一个线程中, 如果不经过适当的同步控制, 直接访问这个结果集合是不安全的.
 */


/**
为了表示当前函数的 接收者(receiver), 我们使用 this 表达式:
— 在 类 的成员函数中, this 指向这个类的当前对象实例.
— 在 扩展函数 中, 或 带接收者的函数字面值(function literal) 中, this 代表调用函数时, 在点号左侧传递的 接收者 参数.
如果 this 没有限定符, 那么它指向 包含当前代码的最内层范围. 如果想要指向其他范围内的 this, 需要使用 标签限定符:

 */

class A { // 隐含的标签 @A
    inner class B { // 隐含的标签 @B
        fun Int.foo() { // 隐含的标签 @foo
            val a = this@A // 指向 A 的 this
            val b = this@B // 指向 B 的 this
            val c = this // 指向 foo() 函数的接受者, 一个 Int 值
            val c1 = this@foo // 指向 foo() 函数的接受者, 一个 Int 值
            val funLit = lambda@ fun String.() {
                val d = this // 指向 funLit 的接受者
            }
            val funLit2 = { s: String ->
                // 指向 foo() 函数的接受者, 因为包含当前代码的 Lambda 表达式没有接受者
                val d1 = this
            }
        }
    }
}