package com.shakespace.kotlinbasic.`04kotlin`

import kotlin.reflect.KProperty

fun main() {
    val b = BaseImpl(10)
    Derived1(b).print()
}

interface Base1 {
    fun print()
}

class BaseImpl(val x: Int) : Base1 {
    override fun print() {
        print(x)
    }
}

class Derived1(b: Base1) : Base1 by b {
    override fun print() {
        print("aaaa")
    }
}

/**
 * delegated property
 * 有许多非常具有共性的属性, 虽然我们可以在每个需要这些属性的类中手工地实现它们,
 * 但是, 如果能够只实现一次, 然后将它放在库中, 供所有需要的类使用, 那将会好很多. 这样的例子包括:
— 延迟加载属性(lazy property): 属性值只在初次访问时才会计算;
— 可观察属性(observable property): 属性发生变化时, 可以向监听器发送通知;
— 将多个属性保存在一个 map 内, 而不是将每个属性保存在一个独立的域内.


委托属性的语法是: val/var <property name>: <Type> by <expression> .
其中 by 关键字之后的表达式就是 委托,
属性的 get() 方法(以 及 set() 方法) 将被委托给这个对象的 getValue() 和 setValue() 方法.
属性委托不必实现任何接口, 但必须提供 getValue() 函数(对于 var 属性, 还需要 setValue()函数

如果属性 p 委托给一个 Delegate 的实例, 那么当我们读取属性值时, 就会调用到 Delegate 的 getValue() 函数,
此时函数收到的第一 个参数将是我们访问的属性 p 所属的对象实例,
第二个参数将是 p 属性本身的描述信息(比如, 你可以从这里得到属性名称).


by lazy 是 标准库中到一个委托函数
by  Delegates.observable()  可观察属性

对于一个 只读 属性 (也就是说, val 属性), 它的委托必须提供一个名为 getValue 的函数, 这个函数接受以下参数:
— thisRef — 这个参数的类型必须与 属性所属的类 相同, 或者是它的基类(对于扩展属性
— 这个参数的类型必须与被扩展的类型相同, 或者是它的基类);
— property — 这个参数的类型必须是 KProperty<*> , 或者是它的基类. 这个函数的返回值类型必须与属性类型相同(或者是它的子类型).

对于一个 值可变(mutable) 属性(也就是说, var 属性), 除 getValue 函数之外, 它的委托还必须 另外再 提供一个名为 setValue 的函数, 这个函数接受以下参数:
— thisRef — 与 getValue() 函数的参数相同;
— property — 与 getValue() 函数的参数相同;
— new value — 这个参数的类型必须与属性类型相同, 或者是它的基类.

委托类可以选择实现 ReadOnlyProperty 接口或 ReadWriteProperty 接口, 其中包含了需要的 operator 方法. 这些接口定义在 Kotlin 标准库内:


provideDelegate 可以用于委托属性控制




 */

class DeleDemo {
    var p: String by StrDelegate()
}

class StrDelegate {
    operator fun getValue(demo: DeleDemo, property: KProperty<*>): String {
        return "$demo, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(deleDemo: DeleDemo, property: KProperty<*>, s: String) {
        println("$deleDemo has been assigned to '${property.name}' in $deleDemo.")
    }

}


//class ResourceDelegate<T> : ReadOnlyProperty<MyUI, T> {
//    override fun getValue(thisRef: MyUI, property: KProperty<*>): T {
//
//    }
//}
//
//class ResourceLoader<T>(id: ResourceID<T>) {
//    operator fun provideDelegate(
//        thisRef: MyUI,
//        prop: KProperty<*>
//    ): ReadOnlyProperty<MyUI, T> {
//        checkProperty(thisRef, prop.name)
//// 创建委托
//        return ResourceDelegate()
//    }
//
//    private fun checkProperty(thisRef: MyUI, name: String) {
////        ...
//    }
//}
//
//class ResourceID<Any> {
//    object image_id {
//
//    }
//
//    object text_id {
//
//    }
//
//}
//
//class MyUI {
//    fun <T> bindResource(id: ResourceID<T>): ResourceLoader<T> {
////        ...
//    }
//
//    val image by bindResource(ResourceID.image_id)
//    val text by bindResource(ResourceID.text_id)
//}




