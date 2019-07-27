package com.shakespace.kotlinbasic.`04kotlin`

/**
 *
 *
 * 数据类必须满足：
— 主构造器至少要有一个参数;
— 主构造器的所有参数必须标记为 val 或 var ;
— 数据类不能是抽象类, open 类, 封闭(sealed)类, 或内部(inner)类;
— (在 Kotlin 1.1 以前) 数据类只允许实现接口.

 */
fun main() {
    //  copy 并修改属性
    val jack = User(name = "Jack", age = 1)
    val olderJack = jack.copy(age = 2)

    //  解构
    val jane = User("Jane", 35)
    val (name, age) = jane
}

//在 JVM 上, 如果自动生成的类需要拥有一个无参数的构造器, 那么需要为所有的属性指定默认值 (参见 构造器).
data class User(val name: String = "", val age: Int = 0)

//注意, 编译器对自动生成的函数, 只使用主构造器中定义的属性. 如果想要在自动生成的函数实现中排除某个属性, 你可以将它声明在类的 主体部:
//在 toString() , equals() , hashCode() , 和 copy() 函数的实现中, 只会使用属性 name , 而且只存在一个组建函数 component1() . 两个 Person 对象可以拥有不同的年龄, 但它们会被认为值相等.

data class Person2(val name: String) {
    var age: Int = 0
}