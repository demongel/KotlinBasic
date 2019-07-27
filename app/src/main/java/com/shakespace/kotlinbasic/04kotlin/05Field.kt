package com.shakespace.kotlinbasic.`04kotlin`

/**
 *
 * 只读属性声明的完整语法与可变属性有两点不同: 由 val 开头, 而不是 var , 并且不允许指定设值方法:


如果属性值在编译期间就能确定, 则可以使用 const 修饰符, 将属性标记为 编译期常数值(compile time constants). 这类属性必须满足以 下所有条件:
— 必须是顶级属性, 或者是一个 object 声明 的成员, 或者是一个 companion object 的成员.
— 值被初始化为 String 类型, 或基本类型(primitive type)
— 不存在自定义的取值方法

 */
fun main() {


}

class Demo {

    var counter = 0 // 注意: 这里的初始化代码直接赋值给后端域变量
        set(value) {
            if (value >= 0)
                field = value
        }

    var setterVisibility: String = "abc"
        private set // 设值方法的可见度为 private, 并使用默认实现

    private var _table: Map<String, Int>? = null
    public val table: Map<String, Int>
        get() {
            if (_table == null) {
                _table = HashMap() // 类型参数可以自动推断得到, 不必指定
            }
            return _table ?: throw AssertionError("Set to null by another thread")
        }

}



