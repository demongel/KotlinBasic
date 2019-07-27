package com.shakespace.kotlinbasic.`04kotlin`

/**
 *
1. 函数, 属性, 类, 对象, 接口, 都可以声明为”顶级的(top-level)”, 也就是说, 直接声明在包之内:
—如果你不指定任何可见度修饰符,默认会使用 public,其含义是,你声明的东西在任何位置都可以访问;
— 如果你将声明的东西标记为 private , 那么它将只在同一个源代码文件内可以访问;
— 如果标记为 internal , 那么它将在同一个模块(module)内的任何位置都可以访问;
— 对于顶级(top-level)声明, protected 修饰符是无效的.

2.
对于类内部声明的成员:
— private 表示只在这个类(以及它的所有成员)之内可以访问;
— protected — 与 private 一样, 另外在子类中也可以访问;
— internal — 在 本模块之内, 凡是能够访问到这个类的地方, 同时也能访问到这个类的 internal 成员;
— public — 凡是能够访问到这个类的地方, 同时也能访问这个类的 public 成员.
Java 使用者 请注意: 在 Kotlin 中, 外部类(outer class)不能访问其内部类(inner class)的 private 成员. 如果你覆盖一个 protected 成员, 并且没有明确指定可见度, 那么覆盖后成员的可见度也将是 protected .


3.局部声明
局部变量, 局部函数, 以及局部类, 都不能指定可见度修饰符.

4. internal 修饰符表示这个成员只能在同一个模块内访问.
更确切地说, 一个模块(module)是指一起编译的一组 Kotlin 源代码文件:
— 一个 IntelliJ IDEA 模块;
— 一个 Maven 工程;
— 一个 Gradle 源代码集(source set)
( test 源代码集例外, 它可以访问 main 中的 internal 声明);
— 通过 <kotlinc> Ant 任务的一次调用编译的一组文件.


 *
 *
 *
 */
fun main() {


}

interface MyInterface {
    val prop: Int // 抽象属性
    val propertyWithImplementation: String
        get() = "foo"

    fun foo() {
        print(prop)
    }
}

class Child : MyInterface {
    override val prop: Int = 29
}