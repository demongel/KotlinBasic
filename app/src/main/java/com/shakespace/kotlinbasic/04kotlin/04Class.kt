package com.shakespace.kotlinbasic.`04kotlin`

/**
 *
 *
 * 与通常的属性一样, 主构造器中定义的属性可以是可变的(var), 也可以是只读的(val).
如果构造器有注解, 或者有可见度修饰符, 这时 constructor 关键字是必须的,
注解和修饰符要放在它之前: class Customer public @Inject constructor(name: String) { ... }

 *
 * 如果一个非抽象类没有声明任何主构造器和次级构造器, 它将带有一个自动生成的,
 * 无参数的主构造器. 这个构造器的可见度为 public. 如 果不希望你的类带有 public 的构造器, 你需要声明一个空的构造器, 并明确设置其可见度:
class DontCreateMe private constructor () { ... }
 *

类中可以包含以下内容:
— 构造器和初始化代码块
— 函数
— 属性
— 嵌套类和内部类
— 对象声明

Any 不是 java.lang.Object;
尤其要注意, 除 equals(), hashCode() 和 toString() 之外,
它没有任何成员.

 *
可以使用一个 var 属性覆盖一个 val 属性, 但不可以反过来使用一个 val 属性覆盖一个 var 属性.
允许这种覆盖的原因是, val 属性本质上只是定义了一个 get 方法,
使用 var 属性来覆盖它, 只是向后代类中添加了一个 set 方法.

 *
 * 与 Java 或 C# 不同, Kotlin 的类没有静态方法(static method). 大多数情况下,
 * 建议使用包级函数(package-level function)替代静态方法.
 *
 *
 *
 */
fun main() {

//    Constructors(1)

    println("Constructing Derived(\"hello\", \"world\")")
    val d = Derived("hello", "world")

}


class Empty


class Person(val name: String) {

    init {

    }
}

class Constructors {
    init {
        println("Init block")
    }

    constructor(i: Int) {
        println("Constructor")
    }
}


open class Base(val name: String) {
    init {
        println("Initializing Base") //  2
    }

    open val size: Int =
        name.length.also { println("Initializing size in Base: $it") } //3  ???
}

class Derived(
    name: String,
    val lastName: String
) : Base(name.capitalize().also { println("Argument for Base: $it") }) { // 最先调用
    init {
        println("Initializing Derived") // 4
    }

    override val size: Int =
        (super.size + lastName.length).also { println("Initializing size in Derived: $it") }
}

open class Foo {
    open fun f() {
        println("Foo.f()")
    }

    open val x: Int get() = 1
}

class Bar : Foo() {
    override fun f() { /* ... */
    }

    override val x: Int get() = 0

    inner class Baz {
        fun g() {
            super@Bar.f() // 调用 Foo 类中的 f() 函数实现
            println(super@Bar.x) // 使用 Foo 类中的 x 属性取值方法实现 }
        }
    }

}